package com.example.team_sudoku_api.entities;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Log {
    @Id
    private String id;

    private LocalDateTime createdAt;

    @NotNull
    private String result; // success,failure

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    @JoinColumn(name = "team_id",referencedColumnName = "team_id")
    private UserTeam userTeam;

    @ManyToOne
    @JoinColumn(name = "cell_board_id",referencedColumnName = "board_id")
    @JoinColumn(name = "cell_row",referencedColumnName = "row")
    @JoinColumn(name = "cell_column",referencedColumnName = "column")
    private Cell cell;
}
