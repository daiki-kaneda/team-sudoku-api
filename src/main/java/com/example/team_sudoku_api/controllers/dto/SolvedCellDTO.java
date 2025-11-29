package com.example.team_sudoku_api.controllers.dto;

import java.time.LocalDateTime;

public record SolvedCellDTO(
    String userName,
    LocalDateTime solvedAt,
    int row,
    int column,
    int value
) {
    
}
