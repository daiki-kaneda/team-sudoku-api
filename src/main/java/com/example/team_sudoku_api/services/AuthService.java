package com.example.team_sudoku_api.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.team_sudoku_api.drivers.FirebaseAuthDriver;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadOrCreateUser(String idToken) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuthDriver.verifyToken(idToken);
        return userRepository.findById(token.getUid())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUid(token.getUid());
                    newUser.setName(token.getName());
                    newUser.setEmail(token.getEmail());
                    newUser.setCreatedAt(LocalDateTime.now());
                    return newUser;
                });

    }
}
