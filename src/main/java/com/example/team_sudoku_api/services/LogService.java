package com.example.team_sudoku_api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.Log;
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
}
