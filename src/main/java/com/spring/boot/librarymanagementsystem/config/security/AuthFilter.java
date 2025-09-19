package com.spring.boot.librarymanagementsystem.config.security;


import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
import java.util.Objects;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Lazy
    @Autowired
    private TokenHandler tokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException {
        try {
            //1- get token from headers
            String token = request.getHeader("Authorization");
            //2- check token
            if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
                response.reset();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setHeader("WWW-Authenticate", "Bearer");
                return;
            }
            token = token.substring(7);
            //3- validate token
            UserSystemDto userValidated = null;
            userValidated = tokenHandler.validateToken(token);
            if (Objects.isNull(userValidated) || !userValidated.getEnabled()) {
                response.reset();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            //4- get roles
            List<SimpleGrantedAuthority> roles = userValidated.getRoles().stream().map(
                    role -> new SimpleGrantedAuthority("ROLE_" + role.getRole())
            ).toList();
            //5- encapsulate user data , used to store details about an authenticated user after authentication is complete.
            //Stored in the SecurityContextHolder to represent the authenticated user for the duration of the request.
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userValidated,
                    userValidated.getPassword(),
                    roles
            );
            //6- The SecurityContextHolder stores UsernamePasswordAuthenticationToken to make the authenticated user’s details available throughout the request.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //7- Continue with the filter chain
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            throw new ServletException("Authentication filter failed", ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().contains("auth/login")
               || request.getRequestURI().contains("auth/sign-up");
    }
}
