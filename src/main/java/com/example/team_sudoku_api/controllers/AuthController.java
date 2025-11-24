package com.example.team_sudoku_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_sudoku_api.controllers.dto.AuthLoginRequest;
import com.example.team_sudoku_api.controllers.dto.AuthLoginResponse;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.services.UserService;
import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) throws FirebaseAuthException {
        User user = userService.loadOrCreateUser(request.idToken());
        return ResponseEntity.ok(new AuthLoginResponse(
                user.getUid(),
                "Login success!"));
    }

}
