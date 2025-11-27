package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.entities.Board;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.Cell.CellId;
import com.example.team_sudoku_api.entities.UserTeam.UserTeamId;
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
    public Board createNewBoard(List<Cell> cells) {
        Board newBoard = Board.create();
        newBoard.setAllCells(cells);
        return boardRepository.save(newBoard);
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
    public void tryValue(String boardId, UserTeamId userTeamId, CellId cellId,int value){
        Board board = boardRepository.findById(boardId).orElseThrow();
        boolean isCorrect = board.tryValue(cellId.getRow(), cellId.getColumn(), value);
        logService.addLog(userTeamId, cellId, isCorrect);
    }
}
