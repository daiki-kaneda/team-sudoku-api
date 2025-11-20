package com.example.team_sudoku_api.entities;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Cell {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "cell", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Log> log = new ArrayList<>();

    @Min(value = 0)
    @Max(value = 8)
    private int row;

    @Min(value = 0)
    @Max(value = 8)
    private int column;

    @Min(value = 1)
    @Max(value = 9)
    private Integer value;

    @Min(value = 1)
    @Max(value = 9)
    private int correctValue;
}
