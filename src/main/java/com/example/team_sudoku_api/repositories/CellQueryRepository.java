package com.example.team_sudoku_api.repositories;

import org.springframework.data.repository.Repository;

import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.Cell.CellId;

import java.util.Optional;


public interface CellQueryRepository extends Repository<Cell,Cell.CellId>{
    Optional<Cell> findById(CellId id);
}
