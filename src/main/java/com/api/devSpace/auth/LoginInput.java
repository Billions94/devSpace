package com.api.devSpace.auth;

import lombok.Data;

@Data
public class LoginInput {
    private String email;
    private String password;
}
