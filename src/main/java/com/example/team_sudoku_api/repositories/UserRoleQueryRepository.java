package com.example.team_sudoku_api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.example.team_sudoku_api.entities.Role;
import com.example.team_sudoku_api.entities.UserRole;
import com.example.team_sudoku_api.entities.UserRole.UserRoleId;
import java.util.List;

// read-only
public interface UserRoleQueryRepository extends Repository<UserRole,UserRoleId>{
    @Query("SELECT ur.role FROM UserRole ur WHERE ur.user.uid = :uid")
    List<Role> findDistinctRolesByUid(String uid);
}
