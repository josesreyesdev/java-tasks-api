package com.jsrdev.TasksAPI.infra.security;

import com.jsrdev.TasksAPI.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var token = this.recoverToken(request);
        if (token != null) {
            // Verificar usuario si tiene sesion
            var login = tokenService.validateToken(token);

            //verificar si es token válido para el user
            var user = userRepository.findByLogin(login);

            // forzamos inicio de sesion
            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        //obtener token del header Authorization
        var authHeader = request.getHeader("Authorization");
        //Evaluar si el token es valido
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}
