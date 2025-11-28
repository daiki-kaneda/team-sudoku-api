package com.example.team_sudoku_api.controllers.dto;

import java.util.List;

public record BoardDTO(String id,String title,List<CellDTO> cells) {

}