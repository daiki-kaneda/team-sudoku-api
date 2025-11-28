package com.example.team_sudoku_api.controllers.dto;

public record TryValueRequest(
    int row,
    int column,
    int value){
    
}
