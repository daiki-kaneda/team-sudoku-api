package com.example.team_sudoku_api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.controllers.dto.LogDataDTO;
import com.example.team_sudoku_api.controllers.dto.SolvedCellDTO;
import com.example.team_sudoku_api.entities.Cell;
import com.example.team_sudoku_api.entities.Log;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserTeam;
import com.example.team_sudoku_api.entities.Cell.CellId;
import com.example.team_sudoku_api.entities.UserTeam.UserTeamId;
import com.example.team_sudoku_api.repositories.CellQueryRepository;
import com.example.team_sudoku_api.repositories.LogRepository;
import com.example.team_sudoku_api.repositories.UserTeamQueryRepository;

import jakarta.persistence.EntityNotFoundException;

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
        UserTeam userTeam = userTeamQueryRepository.findById(userTeamId).orElseThrow(()->new EntityNotFoundException());
        Cell cell = cellQueryRepository.findById(cellId).orElseThrow(()->new EntityNotFoundException());
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

    public List<SolvedCellDTO> getTeamBoardStateByTeamId(String teamId){
        List<Log> logs = logRepository.findByTeamIdAndSuccessResult(teamId);
        Map<String, SolvedCellDTO> latestStateMap = new HashMap<>();

        for (Log l : logs) {
            User user = l.getUserTeam().getUser();
            Cell cell = l.getCell();
            
            String key = cell.getId().getRow() + "-" + cell.getId().getColumn();
            
            SolvedCellDTO dto = new SolvedCellDTO(
                user.getName(),
                l.getCreatedAt(),
                cell.getId().getRow(),
                cell.getId().getColumn(),
                cell.getCorrectValue() 
            );

            latestStateMap.put(key, dto);
        }
        return new ArrayList<>(latestStateMap.values());
    }
}
