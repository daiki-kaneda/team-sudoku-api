package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.Cell.CellId;

public interface CellRepository extends JpaRepository<Cell, CellId> {
    
}
