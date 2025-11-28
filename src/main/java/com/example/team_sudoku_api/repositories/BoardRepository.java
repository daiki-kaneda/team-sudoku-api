package com.example.team_sudoku_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.Board;


public interface BoardRepository extends JpaRepository<Board, String> {
    Page<Board> findByTitleContaining(String title,Pageable pageable);
}
