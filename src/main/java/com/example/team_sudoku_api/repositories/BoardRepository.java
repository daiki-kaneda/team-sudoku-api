package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.Board;

public interface BoardRepository extends JpaRepository<Board, String> {
    
}
