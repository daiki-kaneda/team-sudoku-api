package com.example.team_sudoku_api.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.team_sudoku_api.drivers.FirebaseAuthDriver;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Component
public class FirebaseAuthenticationProvider {

    public Authentication createAuthentication(String idToken) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuthDriver.verifyToken(idToken);
        String uid = token.getUid();
        String role = "USER";

        return new UsernamePasswordAuthenticationToken(uid, null, List.of(
                new SimpleGrantedAuthority("ROLE_" + role)));
    }

}
