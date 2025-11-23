package com.example.team_sudoku_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.entities.Role;
import com.example.team_sudoku_api.repositories.UserRoleRepository;

@Service
public class UserRoleService {
    private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository=userRoleRepository;
    }

    public List<Role> getRolesByUid(String uid){
        List<Role> roles = userRoleRepository.findDistinctByUserUid(uid);
        return roles;
    }
}
