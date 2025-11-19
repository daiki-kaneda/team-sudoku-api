package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.Log;

public interface LogRepository extends JpaRepository<Log, String> {
    
}
