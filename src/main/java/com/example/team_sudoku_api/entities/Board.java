package com.example.team_sudoku_api.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    private String id;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<Cell> cells = new ArrayList<>();
}
