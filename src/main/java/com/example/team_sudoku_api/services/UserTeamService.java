package com.example.team_sudoku_api.services;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.repositories.UserTeamRepository;

@Service
public class UserTeamService {
    private UserTeamRepository userTeamRepository;

    public UserTeamService(UserTeamRepository userTeamRepository) {
        this.userTeamRepository = userTeamRepository;
    }
}
