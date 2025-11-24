package com.example.team_sudoku_api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team_sudoku_api.drivers.FirebaseAuthDriver;
import com.example.team_sudoku_api.entities.Role;
import com.example.team_sudoku_api.entities.User;
import com.example.team_sudoku_api.entities.UserRole;
import com.example.team_sudoku_api.entities.UserRole.UserRoleId;
import com.example.team_sudoku_api.repositories.RoleQueryRepository;
import com.example.team_sudoku_api.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
@Transactional(readOnly = true)
public class UserService {
    private UserRepository userRepository;
    private RoleQueryRepository roleRepository;

    public UserService(UserRepository userRepository,
            RoleQueryRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // FirebaseTokenに対応するユーザがデータベースに存在する場合は、それを返し、存在しない場合は、新しく[ROLE_USER権限]ユーザをデータベースに保存して返す。
    @Transactional
    public User loadOrCreateUser(String idToken) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuthDriver.verifyToken(idToken);
        String uid = token.getUid();
        Optional<User> user = userRepository.findById(uid);
        if (user.isPresent()) {
            return user.get();
        } else {
            User newUser = User.createNewUser(uid, token.getName(), token.getEmail());
            Role role = roleRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new IllegalStateException("ROLE_USER not found in DB."));
            UserRole userRole = UserRole.create(UserRoleId.create(uid, role.getId()), newUser, role);
            newUser.addUserRole(userRole);
            return userRepository.save(newUser);
        }
    }
}
