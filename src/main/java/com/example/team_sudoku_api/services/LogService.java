package com.example.team_sudoku_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.controllers.dto.LogDataDTO;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.Log;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.entities.Cell.CellId;
import com.example.team_sudoku_api.entities.UserTeam.UserTeamId;
import com.example.team_sudoku_api.repositories.CellQueryRepository;
import com.example.team_sudoku_api.repositories.LogRepository;
import com.example.team_sudoku_api.repositories.UserTeamQueryRepository;

@Service
@Transactional(readOnly = true)
public class LogService {
    private LogRepository logRepository;
    private UserTeamQueryRepository userTeamQueryRepository;
    private CellQueryRepository cellQueryRepository;

    public LogService(LogRepository logRepository, UserTeamQueryRepository userTeamQueryRepository,
            CellQueryRepository cellQueryRepository) {
        this.logRepository = logRepository;
        this.userTeamQueryRepository = userTeamQueryRepository;
        this.cellQueryRepository = cellQueryRepository;
    }

    @Transactional
    public void addLog(UserTeamId userTeamId, CellId cellId, boolean isCorrect) {
        UserTeam userTeam = userTeamQueryRepository.findById(userTeamId).orElseThrow();
        Cell cell = cellQueryRepository.findById(cellId).orElseThrow();
        Log newLog = Log.create(isCorrect, userTeam, cell);
        logRepository.save(newLog);
    }

    public Page<LogDataDTO> getLogsByTeamId(String teamId, Pageable pageable) {
        return logRepository.findByTeamId(teamId, pageable)
                .map(l -> {
                    User user = l.getUserTeam().getUser();
                    Cell cell = l.getCell();
                    return new LogDataDTO(
                            user.getName(),
                            l.getCreatedAt(),
                            cell.getId().getRow(),
                            cell.getId().getColumn(),
                            l.getResult().equals("success"));
                });
    }
}
