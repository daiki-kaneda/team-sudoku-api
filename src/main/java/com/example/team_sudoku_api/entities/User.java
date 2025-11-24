package com.example.team_sudoku_api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();

    public void addUserTeam(UserTeam userTeam) {
        this.userTeams.add(userTeam);
    }

    public void addUserRole(UserRole userRole) {
        this.userRoles.add(userRole);
    }

    public static User createNewUser(String uid, String name, String email) {
        User newUser = new User();
        newUser.uid = uid;
        newUser.name = name;
        newUser.email = email;
        newUser.createdAt = LocalDateTime.now();
        return newUser;
    }
}
