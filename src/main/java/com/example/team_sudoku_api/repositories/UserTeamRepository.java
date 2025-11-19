package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.entities.UserTeam.UserTeamId;

public interface UserTeamRepository extends JpaRepository<UserTeam, UserTeamId> {

}
