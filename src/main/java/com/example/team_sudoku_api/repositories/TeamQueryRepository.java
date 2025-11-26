package com.example.team_sudoku_api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.team_sudoku_api.entities.Team;

public interface TeamQueryRepository extends Repository<Team, String> {

    @Query("SELECT t FROM Team t JOIN t.userTeams ut WHERE ut.user.uid = :userId")
    List<Team> findJoinedTeamsByUserId(@Param("userId") String userId);

    Page<Team> findByNameContaining(String name, Pageable pageable);

}
