package com.example.team_sudoku_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_sudoku_api.controllers.dto.TeamsCreateTeamRequest;
import com.example.team_sudoku_api.controllers.dto.TeamsCreateTeamResponse;
import com.example.team_sudoku_api.entities.Team;
import com.example.team_sudoku_api.services.TeamService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamsCreateTeamResponse> createTeam(
            @AuthenticationPrincipal String principal,
            @RequestBody TeamsCreateTeamRequest request) {
        Team newTeam = teamService.createTeamAndJoin(principal, request.name(), request.boardId());

        return ResponseEntity.ok(new TeamsCreateTeamResponse(newTeam.getId()));
    }

}
