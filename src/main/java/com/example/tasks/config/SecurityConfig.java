package com.example.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure security settings
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/login", "/api/users/register").permitAll()  // Allow public access to login and register
                        .anyRequest().authenticated()  // All other requests need authentication
                )
                .csrf(csrf -> csrf.disable());  // Disable CSRF protection for REST APIs

        // Configure HTTP Basic authentication separately (no more httpBasic().and())
        http.httpBasic(basic -> basic.disable()); // Disable HTTP basic authentication (or remove if you want it)

        return http.build();  // No need for "and()" chaining
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        // Here you can configure your authentication manager
        return authenticationManagerBuilder.build();
    }
}
