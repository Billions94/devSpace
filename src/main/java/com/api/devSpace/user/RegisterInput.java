package com.api.devSpace.user;

import com.api.devSpace.user.entity.User;
import lombok.Data;

@Data
public class RegisterInput {
    private User userDetails;
    private String email;
    private String password;
}
