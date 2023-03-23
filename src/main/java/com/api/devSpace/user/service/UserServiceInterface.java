package com.api.devSpace.user.service;

import com.api.devSpace.user.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> users();
    Object user(Long userId);
    Object createUser(User userInput);
    Object updateUser(Long userId, User userInput);
    String  deleteUser(Long userId);
}
