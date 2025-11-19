package com.example.team_sudoku_api.services;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.repositories.CellRepository;

@Service
public class CellService {
    private CellRepository cellRepository;

    public CellService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }
}
