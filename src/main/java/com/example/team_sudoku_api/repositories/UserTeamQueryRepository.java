package com.example.team_sudoku_api.repositories;

import org.springframework.data.repository.Repository;

import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.entities.UserTeam.UserTeamId;

import java.util.Optional;


public interface UserTeamQueryRepository extends Repository<UserTeam,UserTeam.UserTeamId>{
    Optional<UserTeam> findById(UserTeamId id);
}
