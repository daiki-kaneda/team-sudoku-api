package com.example.team_sudoku_api.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.drivers.FirebaseAuthDriver;
import com.example.team_sudoku_api.entities.Role;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserRole;
import com.example.team_sudoku_api.entities.UserRole.UserRoleId;
import com.example.team_sudoku_api.repositories.RoleRepository;
import com.example.team_sudoku_api.repositories.UserRepository;
import com.example.team_sudoku_api.repositories.UserRoleRepository;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class AuthService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User loadOrCreateUser(String idToken) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuthDriver.verifyToken(idToken);
        String uid = token.getUid();
        User user = userRepository.findById(uid)
                .orElseGet(() -> {
                    User newUser = createNewUser(uid, token);
                    return userRepository.save(newUser);
                });

        UserRole userRole = new UserRole();
        Role role = roleRepository.findByRoleName("ROLE_USER").get();
        userRole.setId(UserRoleId.create(uid, role.getId()));
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleRepository.save(userRole);
        return user;
    }

    private User createNewUser(String uid, FirebaseToken token) {
        User newUser = new User();
        newUser.setUid(uid);
        newUser.setName(token.getName());
        newUser.setEmail(token.getEmail());
        newUser.setCreatedAt(LocalDateTime.now());
        return newUser;
    }
}
