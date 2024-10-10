package com.jsrdev.TasksAPI.infra.security;

import com.jsrdev.TasksAPI.domain.models.auth.SignUp;
import com.jsrdev.TasksAPI.domain.models.user.User;
import com.jsrdev.TasksAPI.domain.repository.UserRepository;
import com.jsrdev.TasksAPI.infra.exceptions.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public User signUp(SignUp signUp) throws InvalidJwtException {

        if (userRepository.findByLogin(signUp.login()) != null) {
            throw new InvalidJwtException("Username/Login already exist");
        }

        String encryptedPass = passwordEncoder(signUp.password());
        User user = new User(signUp.login(), encryptedPass, signUp.role());

        return userRepository.save(user);
    }

    private String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
