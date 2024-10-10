package com.jsrdev.TasksAPI.controller;

import com.jsrdev.TasksAPI.domain.models.auth.JWTTokenData;
import com.jsrdev.TasksAPI.domain.models.auth.SignIn;
import com.jsrdev.TasksAPI.domain.models.auth.SignUp;
import com.jsrdev.TasksAPI.domain.models.user.User;
import com.jsrdev.TasksAPI.domain.models.user.UserResponse;
import com.jsrdev.TasksAPI.infra.security.AuthService;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService service;

    @Autowired
    private TokenService tokenService;

    // add user
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @RequestBody @Valid SignUp data,
            UriComponentsBuilder uriBuilder
    ) {
        User user = service.signUp(data);
        URI url = uriBuilder.path("/api/auth/signup/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(url).body(new UserResponse(user)); // status 201-created
        //return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // login
    @PostMapping("/signin")
    public ResponseEntity<JWTTokenData> signIn(@RequestBody @Valid SignIn data) {

        var usernamePassAuth = new UsernamePasswordAuthenticationToken(data.login(), data.pass());

        var authUser = authenticationManager.authenticate(usernamePassAuth);
        var user = (User) authUser.getPrincipal();
        var accessToken = tokenService.generateAccessToken(user);

        return ResponseEntity.ok(new JWTTokenData(accessToken));
    }
}
