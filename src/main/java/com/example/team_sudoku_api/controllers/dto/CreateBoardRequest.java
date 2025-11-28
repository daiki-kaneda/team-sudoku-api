package com.example.team_sudoku_api.controllers.dto;

import java.util.List;

public record CreateBoardRequest(String title,List<CellWithCorrectValueDTO> cells) {
    
}
