package com.api.devSpace.auth;

import com.api.devSpace.config.JwtService;
import com.api.devSpace.exception.PSQLException;
import com.api.devSpace.user.entity.Role;
import com.api.devSpace.user.entity.User;
import com.api.devSpace.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public String register(@NotNull User registerInput) {
        registerInput.setPassword(passwordEncoder.encode(registerInput.getPassword()));
        registerInput.setRole(Role.USER);
        registerInput.setCreatedAt(LocalDateTime.now());

        User user = userRepository.save(registerInput);
        return jwtService.generateToken(user);
    }

    public String login(@NotNull LoginInput loginInput) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInput.getEmail(),
                        loginInput.getPassword()
                )
        );

        return jwtService.generateToken(
                userRepository
                        .findByEmail(loginInput.getEmail())
                        .orElseThrow(() -> new PSQLException("User not found"))
        );
    }
}
