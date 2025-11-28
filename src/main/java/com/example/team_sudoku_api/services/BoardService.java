package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.controllers.dto.BoardDTO;
import com.example.team_sudoku_api.entities.Board;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.repositories.BoardRepository;
import com.example.team_sudoku_api.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private LogService logService;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, LogService logService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.logService = logService;
    }

    @Transactional
    public String createNewBoard(BoardDTO boardDTO) {
        Board newBoard = Board.create(boardDTO.title());
        List<Cell> cells = boardDTO.cells().stream()
                .map(c -> Cell.create(
                        Cell.CellId.create(
                                newBoard.getId(),
                                c.row(), c.column()),
                        newBoard,
                        c.value(),
                        c.correctValue()))
                .toList();
        newBoard.setAllCells(cells);
        boardRepository.save(newBoard);

        return newBoard.getId();
    }

    @Transactional
    public String createNewTeam(String boardId, String name) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        String teamId = board.createNewTeam(name);
        boardRepository.save(board);
        return teamId;
    }

    @Transactional
    public void joinTeam(String boardId, String teamId, String uid) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        User user = userRepository.findById(uid).orElseThrow();
        board.joinTeam(teamId, user);
        boardRepository.save(board);
    }

    @Transactional
    public boolean tryValue(String boardId, String userId, String teamId, int row, int column, int value) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        boolean isCorrect = board.tryValue(row, column, value);
        logService.addLog(UserTeam.UserTeamId.create(userId, teamId), Cell.CellId.create(boardId, row, column),
                isCorrect);
        return isCorrect;
    }
}
