package com.example.team_sudoku_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team_sudoku_api.controllers.dto.UserLoginRequest;
import com.example.team_sudoku_api.controllers.dto.UserLoginResponse;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.services.UserService;
import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) throws FirebaseAuthException {
        User user = userService.loadOrCreateUser(request.idToken());
        return ResponseEntity.ok(new UserLoginResponse(
                user.getUid(),
                "Login success! Your roles are "
                        + user.getUserRoles().stream().map(ur -> ur.getRole().getRoleName()).toList().toString()
                    + "\nYour email is "+user.getEmail()));
    }

}
