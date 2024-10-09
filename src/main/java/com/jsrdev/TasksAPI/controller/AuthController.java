package com.jsrdev.TasksAPI.controller;

import com.jsrdev.TasksAPI.domain.models.auth.AuthData;
import com.jsrdev.TasksAPI.domain.models.auth.JWTTokenData;
import com.jsrdev.TasksAPI.domain.models.user.User;
import com.jsrdev.TasksAPI.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> login(@RequestBody @Valid AuthData sign) {
        var usernamePassAuth = new UsernamePasswordAuthenticationToken(sign.login(), sign.pass());

        var authUser = authenticationManager.authenticate(usernamePassAuth);
        var user = (User) authUser.getPrincipal();
        var accessToken = tokenService.generateAccessToken(user);

        return ResponseEntity.ok(new JWTTokenData(accessToken));
    }
}
