package com.spring.boot.librarymanagementsystem.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@TestConfiguration
public class TestConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        // Always return "test-user" during tests
        return () -> Optional.of("test-user");
    }
}

