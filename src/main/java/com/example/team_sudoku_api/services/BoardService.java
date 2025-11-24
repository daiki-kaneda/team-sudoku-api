package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.entities.Board;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.repositories.BoardRepository;
import com.example.team_sudoku_api.repositories.UserRepository;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Board createNewBoard(List<Cell> cells) {
        Board newBoard = Board.create();
        newBoard.setAllCells(cells);
        return boardRepository.save(newBoard);
    }

    @Transactional
    public void createNewTeam(String boardId, String name) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        board.createNewTeam(name);
        boardRepository.save(board);
    }

    @Transactional
    public void joinTeam(String boardId, String teamId, String uid) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        User user = userRepository.findById(uid).orElseThrow();
        board.joinTeam(teamId, user);
        boardRepository.save(board);
    }
}
