package com.example.team_sudoku_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_sudoku_api.controllers.dto.BoardDTO;
import com.example.team_sudoku_api.controllers.dto.TryValueRequest;
import com.example.team_sudoku_api.controllers.dto.TryValueResponse;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.services.BoardService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class BoardController {
    private BoardService boardService;

    public BoardController(
            BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards/{boardId}/teams/{teamId}/try")
    public TryValueResponse tryValue(
            @PathVariable String boardId,
            @PathVariable String teamId,
            @AuthenticationPrincipal String uid,
            @RequestBody TryValueRequest request) {
        boolean isCorrect = boardService.tryValue(boardId, uid, teamId, request.row(), request.column(),
                request.value());
        return new TryValueResponse(isCorrect);
    }

    @PostMapping("/boards")
    @PreAuthorize("hasRole(ADMIN)")
    public void createNewBoard(@RequestBody BoardDTO board) {
        boardService.createNewBoard(board);
    }

}
