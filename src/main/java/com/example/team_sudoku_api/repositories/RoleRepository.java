package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team_sudoku_api.entities.Role;
import java.util.List;


public interface RoleRepository extends JpaRepository<Role,Long>{
    List<Role> findByRoleName(String roleName);
}
