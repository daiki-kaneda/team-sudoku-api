package com.example.team_sudoku_api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity<String> {
    @Id
    private String id;
    @Size(min = 3, max = 20)
    private String name;
    private LocalDateTime createdAt;

    private boolean isActive = true;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserTeam> userTeams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void join(User user) {
        UserTeam userTeam = UserTeam.create(user.getUid(), id);
        this.userTeams.add(userTeam);
        userTeam.setTeam(this);
        userTeam.setUser(user);
    }

    public static Team create(String name) {
        Team team = new Team();
        team.id = UUID.randomUUID().toString();
        team.name = name;
        team.createdAt = LocalDateTime.now();
        return team;
    }

    @Override
    public String getId() {
        return id;
    }
}