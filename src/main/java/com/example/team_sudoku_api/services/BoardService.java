package com.example.team_sudoku_api.services;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.repositories.BoardRepository;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
}
