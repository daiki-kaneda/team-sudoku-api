package com.example.team_sudoku_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_sudoku_api.controllers.dto.CreateTeamRequest;
import com.example.team_sudoku_api.controllers.dto.CreateTeamResponse;
import com.example.team_sudoku_api.controllers.dto.LogDataDTO;
import com.example.team_sudoku_api.controllers.dto.SolvedCellDTO;
import com.example.team_sudoku_api.controllers.dto.TeamDTO;
import com.example.team_sudoku_api.services.BoardService;
import com.example.team_sudoku_api.services.LogService;
import com.example.team_sudoku_api.services.TeamQueryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class TeamController {
    private BoardService boardService;
    private TeamQueryService teamQueryService;
    private LogService logService;

    public TeamController(BoardService boardService,TeamQueryService teamQueryService,LogService logService){
        this.boardService=boardService;
        this.teamQueryService=teamQueryService;
        this.logService=logService;
    }

    @PostMapping("/boards/{boardId}/teams")
    public CreateTeamResponse createTeam(
        @PathVariable String boardId,
        @RequestBody CreateTeamRequest request
    ) {
       String teamId =  boardService.createNewTeam(boardId, request.name());
       return new CreateTeamResponse(teamId);
    }

    @PostMapping("/boards/{boardId}/teams/{teamId}/join")
    public void joinTeam(
        @PathVariable String boardId,
        @PathVariable String teamId,
        @AuthenticationPrincipal String uid
    ) {
        boardService.joinTeam(boardId,teamId,uid);
    }

    @GetMapping("/teams")
    public Page<TeamDTO> getTeamsNameContaining(
        @RequestParam(required = false) String name,
        @PageableDefault(size = 20) Pageable pageable
    ) {
        return teamQueryService.getTeamsNameContaining(name!=null ? name:"", pageable);
    }
    @GetMapping("/teams/{teamId}/logs")
    public Page<LogDataDTO> getLogsByTeamId(
        @PathVariable String teamId,
        @PageableDefault(size = 20) Pageable pageable
    ) {
        return logService.getLogsByTeamId(teamId, pageable);
    }

    @GetMapping("/teams/{teamId}/board-state")
    public List<SolvedCellDTO> getBoardStateByTeamId(
        @PathVariable String teamId
    ) {
        return logService.getTeamBoardStateByTeamId(teamId);
    }

    @GetMapping("teams/mine")
    public List<TeamDTO> getMyTeams(@AuthenticationPrincipal String uid) {
        return teamQueryService.getJoinedTeamByUid(uid);
    }
}
