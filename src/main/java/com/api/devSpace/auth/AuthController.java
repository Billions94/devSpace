package com.api.devSpace.auth;

import com.api.devSpace.config.ConfigProperties;
import com.api.devSpace.config.JwtService;
import com.api.devSpace.exception.PSQLException;
import com.api.devSpace.user.entity.User;
import com.api.devSpace.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register")
    public String register(@RequestBody User registerInput) {
        return authService.register(registerInput);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginInput loginInput) {
        return authService.login(loginInput);
    }
}
