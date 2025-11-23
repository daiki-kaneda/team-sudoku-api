package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.Role;
import com.example.team_sudoku_api.entities.UserRole;
import com.example.team_sudoku_api.entities.UserRole.UserRoleId;
import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole,UserRoleId>{
    List<Role> findDistinctByUserUid(String uid);
}
