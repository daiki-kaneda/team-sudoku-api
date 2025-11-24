package com.example.team_sudoku_api.repositories;

import org.springframework.data.repository.Repository;

import com.example.team_sudoku_api.entities.Role;
import com.example.team_sudoku_api.entities.UserRole;
import com.example.team_sudoku_api.entities.UserRole.UserRoleId;
import java.util.List;

// read-only
public interface UserRoleQueryRepository extends Repository<UserRole,UserRoleId>{
    List<Role> findDistinctByUserUid(String uid);
}
