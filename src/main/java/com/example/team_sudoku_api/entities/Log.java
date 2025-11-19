package com.example.team_sudoku_api.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Log {
    @Id
    private String id;

    private LocalDateTime createdAt;
    private String result; // success,failure

    @ManyToOne
    @JoinColumn(name = "user_team")
    private UserTeam userTeam;

    @ManyToOne
    @JoinColumn(name = "cell_id")
    private Cell cell;
}
