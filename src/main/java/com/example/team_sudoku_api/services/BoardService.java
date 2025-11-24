package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.entities.Board;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.repositories.BoardRepository;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board createNewBoard(List<Cell> cells){
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
}
