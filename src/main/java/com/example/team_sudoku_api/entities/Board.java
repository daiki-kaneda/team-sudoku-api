package com.example.team_sudoku_api.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    private String id;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Cell> cells = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<>();
}
