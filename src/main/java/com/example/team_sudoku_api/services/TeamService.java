package com.example.team_sudoku_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.entities.Board;
import com.example.team_sudoku_api.entities.Team;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.entities.UserTeam.UserTeamId;
import com.example.team_sudoku_api.repositories.BoardRepository;
import com.example.team_sudoku_api.repositories.TeamRepository;
import com.example.team_sudoku_api.repositories.UserRepository;
import com.example.team_sudoku_api.repositories.UserTeamRepository;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private UserTeamRepository userTeamRepository;

    public TeamService(TeamRepository teamRepository, BoardRepository boardRepository, UserRepository userRepository,
            UserTeamRepository userTeamRepository) {
        this.teamRepository = teamRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.userTeamRepository = userTeamRepository;
    }

    // ユーザが自分のみが所属するチームを新しく作る
    @Transactional
    public Team createTeamAndJoin(String uid, String name, String boardId) {
        String teamId = UUID.randomUUID().toString();
        Board board = boardRepository.findById(boardId).orElseThrow();
        Team newTeam = new Team(teamId, name, LocalDateTime.now(), true, List.of(), board);

        teamRepository.save(newTeam);

        User user = userRepository.findById(uid).orElseThrow();

        UserTeamId id = new UserTeamId();
        id.setUserId(uid);
        id.setTeamId(teamId);
        UserTeam userTeam = new UserTeam();
        userTeam.setId(id);
        userTeam.setJoinedAt(LocalDateTime.now());
        userTeam.setUser(user);
        userTeam.setTeam(newTeam);

        userTeamRepository.save(userTeam);
        return newTeam;
    }

}
