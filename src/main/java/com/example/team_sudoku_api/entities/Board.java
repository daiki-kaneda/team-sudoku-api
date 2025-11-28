package com.example.team_sudoku_api.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity<String> {
    @Id
    private String id;
    @NotNull
    private String title;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cell> cells = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams = new ArrayList<>();

    public void setAllCells(List<Cell> newCells) {
        if (newCells.size() != 81) {
            throw new IllegalArgumentException("Board must have exactly 81 cells");
        }

        this.cells.clear();
        this.cells.addAll(newCells);

        this.cells.forEach(cell -> cell.setBoard(this));
    }

    public String createNewTeam(String name) {
        Team newTeam = Team.create(name);
        this.teams.add(newTeam);
        newTeam.setBoard(this);
        return newTeam.getId();
    }

    public void joinTeam(String teamId, User user) {
        Team team = this.teams.stream().filter(t -> t.getId().equals(teamId)).findFirst().orElseThrow();
        team.join(user);
    }

    public boolean tryValue(int row, int column, int value) {
        if (row < 0 || row > 8 || column < 0 || column > 8 || value < 1 || value > 9) {
            throw new IllegalArgumentException("Must be 0<=row,column<=8, 1<=value<=9");
        }

        return cells.stream()
                .filter(c -> c.getId().getRow() == row && c.getId().getColumn() == column)
                .findFirst()
                .map(cell -> cell.getCorrectValue() == value)
                .orElseThrow(() -> new IllegalStateException("Cell data is missing"));
    }

    public static Board create(String title) {
        Board board = new Board();
        board.id = UUID.randomUUID().toString();
        board.title = title;
        return board;
    }

    @Override
    public String getId() {
        return id;
    }
}