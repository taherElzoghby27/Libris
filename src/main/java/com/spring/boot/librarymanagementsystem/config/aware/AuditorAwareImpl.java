package com.spring.boot.librarymanagementsystem.config.aware;

import com.spring.boot.librarymanagementsystem.dto.UserDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if user is authenticated and principal is AccountDto
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDto acc) {

            return Optional.ofNullable(acc.getUsername());
        }
        // Fallback user or anonymous actions like sign up
        return Optional.empty();
    }
}