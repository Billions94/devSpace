package com.api.devSpace.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record ConfigProperties(String jwtSecret) {
}
