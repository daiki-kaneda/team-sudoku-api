package com.example.team_sudoku_api.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.team_sudoku_api.entities.Cell.CellId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log extends BaseEntity<String> {
    @Id
    private String id;

    private LocalDateTime createdAt;

    @NotNull
    private String result; // success,failure

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private UserTeam userTeam;

    @ManyToOne
    @JoinColumn(name = "cell_board_id", referencedColumnName = "board_id")
    @JoinColumn(name = "cell_row", referencedColumnName = "row_idx")
    @JoinColumn(name = "cell_column", referencedColumnName = "col_idx")
    private Cell cell;

    public static Log create(boolean isCorrect, UserTeam userTeam, Cell cell) {
        Log log = new Log();
        log.id = UUID.randomUUID().toString();
        log.createdAt = LocalDateTime.now();
        log.result = isCorrect ? "success":"failure";
        log.userTeam = userTeam;
        log.cell = cell;
        return log;
    }

    @Override
    public String getId() {
        return id;
    }
}
