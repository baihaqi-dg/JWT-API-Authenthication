package com.example.JWT.API.Authenthication.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                final String authHeader = request.getHeader("Authorization");
                final String jwt;
                final String userEmail;
                if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
                  filterChain.doFilter(request, response);
                  return;
                }
                jwt = authHeader.substring(7);
                userEmail = jwtService.extractUsername(jwt);
    }
    
}
