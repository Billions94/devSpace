package com.api.devSpace.user.resolver;


import com.api.devSpace.user.entity.User;
import com.api.devSpace.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserResolver {
    private final UserService userService;

    @QueryMapping
    List<User> users() {
        return  userService.users();
    }

    @QueryMapping
    Object user(@Argument("userId") Long userId) {
        return userService.user(userId);
    }

    @MutationMapping
    Object createUser(@Argument("userInput") User userInput) {
        return userService.createUser(userInput);
    }

    @MutationMapping
    Object updateUser(
            @Argument("userId") Long userId,
            @Argument("userInput") User userInput
    ) {
        return userService.updateUser(userId, userInput);
    }

    @MutationMapping
    String deleteUser(@Argument("userId") Long userId) {
        return userService.deleteUser(userId);
    }
}
