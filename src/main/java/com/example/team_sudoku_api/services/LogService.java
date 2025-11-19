package com.example.team_sudoku_api.services;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.repositories.LogRepository;

@Service
public class LogService {
    private LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
}
