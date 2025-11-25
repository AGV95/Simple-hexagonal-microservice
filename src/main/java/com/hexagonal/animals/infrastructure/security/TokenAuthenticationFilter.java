package com.hexagonal.animals.infrastructure.security;

import com.hexagonal.animals.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token != null && tokenService.isValidToken(token)) {
            String username = tokenService.getUsernameFromToken(token);


            // Se podría cargar más info del usuario para asignar roles
            UserDetails userDetails = new User(username, "pw",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

            // Spring Security requiere un objeto de Authentication para "loguear" al usuario
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Se establece en el contexto de seguridad de Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continúa la cadena de filtros
        filterChain.doFilter(request, response);
    }

}
