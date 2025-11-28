package com.example.team_sudoku_api.controllers.dto;

public record CellWithCorrectValueDTO(int row, int column, Integer value, int correctValue) {
    
}
