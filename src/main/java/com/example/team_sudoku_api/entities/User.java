package com.example.team_sudoku_api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private String uid;

    private String name;
    private String email;
    private LocalDateTime createdAt;

    private boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserTeam> userTeams = new ArrayList<>();
}
