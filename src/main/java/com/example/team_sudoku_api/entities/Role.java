package com.example.team_sudoku_api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    private Long id;

    private String roleName;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();
}
