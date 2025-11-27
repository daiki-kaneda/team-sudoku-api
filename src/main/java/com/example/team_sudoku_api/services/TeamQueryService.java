package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.controllers.dto.TeamDTO;
import com.example.team_sudoku_api.repositories.TeamQueryRepository;

@Service
@Transactional(readOnly=true)
public class TeamQueryService {
    private TeamQueryRepository teamQueryRepository;

    public TeamQueryService(TeamQueryRepository teamQueryRepository) {
        this.teamQueryRepository = teamQueryRepository;
    }

    public List<TeamDTO> getJoinedTeamByUid(String uid) {
        return teamQueryRepository.findJoinedTeamsByUserId(uid)
        .stream().map(team -> new TeamDTO(team.getId(),team.getName())).toList();
    }

    public Page<TeamDTO> getTeamsNameContaining(String name, Pageable pageable) {
        return teamQueryRepository.findByNameContaining(name, pageable)
        .map(team -> new TeamDTO(team.getId(),team.getName()));
    }
}
