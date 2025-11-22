package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.UserRole;
import com.example.team_sudoku_api.entities.UserRole.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole,UserRoleId>{
    
}
