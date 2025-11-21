package com.example.team_sudoku_api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Cell {
    @EmbeddedId
    private CellId id;

    @ManyToOne
    @JoinColumn(name = "board_id",referencedColumnName = "id")
    @MapsId("boardId")
    private Board board;

    @OneToMany(mappedBy = "cell", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Log> log = new ArrayList<>();

    @Min(value = 1)
    @Max(value = 9)
    private Integer value;

    @Min(value = 1)
    @Max(value = 9)
    private int correctValue;

    @Data
    @Embeddable
    public static class CellId implements Serializable {
        @Column(name = "board_id")
        private String boardId;

        @Column(name = "row")
        @Min(value = 0)
        @Max(value = 8)
        private int row;

        @Column(name = "column")
        @Min(value = 0)
        @Max(value = 8)
        private int column;
    }
}
