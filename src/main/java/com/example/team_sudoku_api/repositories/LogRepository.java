package com.example.team_sudoku_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.team_sudoku_api.entities.Log;

public interface LogRepository extends JpaRepository<Log, String> {
    @Query(value = "SELECT l FROM Log l " +
            "JOIN FETCH l.userTeam ut " +
            "JOIN FETCH ut.user " +
            "JOIN FETCH l.cell " +
            "WHERE ut.id.teamId = :teamId", countQuery = "SELECT COUNT(l) FROM Log l WHERE l.userTeam.id.teamId = :teamId")
    Page<Log> findByTeamId(@Param("teamId") String teamId, Pageable pageable);
}
