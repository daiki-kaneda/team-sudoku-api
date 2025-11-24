package com.example.team_sudoku_api.repositories;

import org.springframework.data.repository.Repository;

import com.example.team_sudoku_api.entities.Role;
import java.util.Optional;

// read-only
public interface RoleQueryRepository extends Repository<Role,Long>{
    Optional<Role> findByRoleName(String roleName);
}
