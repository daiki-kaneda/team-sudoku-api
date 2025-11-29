package com.example.team_sudoku_api.controllers.dto;

import java.time.LocalDateTime;

public record LogDataDTO(
    String userName,
    LocalDateTime createdAt,
    int row,
    int column,
    boolean isCorrect
) {
    
}
