package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.entities.Team;
import com.example.team_sudoku_api.repositories.TeamQueryRepository;

@Service
public class TeamQueryService {
    private TeamQueryRepository teamQueryRepository;

    public TeamQueryService(TeamQueryRepository teamQueryRepository) {
        this.teamQueryRepository = teamQueryRepository;
    }

    public List<Team> getJoinedTeamByUid(String uid) {
        return teamQueryRepository.findJoinedTeamsByUserId(uid);
    }

    public Page<Team> getTeamsNameContaining(String name, Pageable pageable) {
        return teamQueryRepository.findByNameContaining(name, pageable);
    }
}
