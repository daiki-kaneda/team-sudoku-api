package com.example.team_sudoku_api.services;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.repositories.TeamRepository;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
