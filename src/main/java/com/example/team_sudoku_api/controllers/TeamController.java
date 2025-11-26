package com.example.team_sudoku_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_sudoku_api.services.BoardService;
import com.example.team_sudoku_api.services.TeamQueryService;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private BoardService boardService;
    private TeamQueryService teamQueryService;
}
