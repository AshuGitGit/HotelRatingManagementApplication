package com.lcwd.gateway.controllers;

import com.lcwd.gateway.models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(Authentication authentication) {

        AuthResponse authResponse = new AuthResponse();

        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            authResponse.setSubject(oidcUser.getSubject());
            authResponse.setEmail(oidcUser.getEmail());
        }
        else if (authentication.getPrincipal() instanceof Jwt jwt) {
            authResponse.setSubject(jwt.getSubject());
        }

        return ResponseEntity.ok(authResponse);
    }

    /*@GetMapping("/login")
    ResponseEntity<AuthResponse> login(@AuthenticationPrincipal Jwt jwt)
    {
        AuthResponse authResponse = new AuthResponse();

        authResponse.setSubject(jwt.getSubject());

        return ResponseEntity.ok(authResponse);
    }*/


}
