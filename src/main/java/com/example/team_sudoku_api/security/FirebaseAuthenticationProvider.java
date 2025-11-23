package com.example.team_sudoku_api.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.team_sudoku_api.drivers.FirebaseAuthDriver;
import com.example.team_sudoku_api.services.UserRoleService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Component
public class FirebaseAuthenticationProvider {

    private UserRoleService userRoleService;

    public FirebaseAuthenticationProvider(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public Authentication createAuthentication(String idToken) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuthDriver.verifyToken(idToken);
        String uid = token.getUid();
        List<? extends GrantedAuthority> authorities = userRoleService.getRolesByUid(uid)
                .stream()
                .map((r) -> new SimpleGrantedAuthority(r.getRoleName()))
                .toList();

        return new UsernamePasswordAuthenticationToken(uid, null, authorities);
    }

}
