package com.api.devSpace.user.service;

import com.api.devSpace.exception.PSQLException;
import com.api.devSpace.response.Failed;
import com.api.devSpace.response.Success;
import com.api.devSpace.user.RegisterInput;
import com.api.devSpace.user.entity.Role;
import com.api.devSpace.user.entity.User;
import com.api.devSpace.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> users() {
        return userRepository.findAll();
    }

    public Object user(Long userId) {
        try {
            User user = userRepository
                    .findById(userId)
                    .orElseThrow(() -> new PSQLException("User with id: " + userId + " does not exist"));

            return new Success(user);
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object createUser(RegisterInput registerInput) {
        try {
            if (userRepository.existsByUsername(registerInput.getUserDetails().getUsername()))
                throw new PSQLException("Username is already taken");

            if (userRepository.existsByEmail(registerInput.getEmail()))
                throw new PSQLException("Email is already taken");

            if (registerInput.getPassword().length() <= 0) {
                throw new PSQLException("Password field cannot be empty");
            } else if (registerInput.getPassword().length() < 8) {
                throw new PSQLException("Password must be at least 8 characters long !!");
            }

            registerInput.getUserDetails().setRole(Role.USER);
            registerInput.getUserDetails().setEmail(registerInput.getEmail());
            registerInput.getUserDetails()
                    .setPassword(passwordEncoder.encode(registerInput.getPassword()));
            registerInput.getUserDetails().setCreatedAt(LocalDateTime.now());

            User user = userRepository.save(registerInput.getUserDetails());
            return new Success(user.getId());
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public Object updateUser(Long userId, User userInput) {
        try {
            User user = userRepository
                    .findById(userId)
                    .orElseThrow(() -> new PSQLException("User with id: " + userId + " does not exist"));

            System.out.println(userInput.getUsername());

            if (userRepository.existsByUsername(userInput.getUsername()))
                throw new PSQLException("Username already exists");

            if (userRepository.existsByEmail(userInput.getEmail()))
                throw new PSQLException("Email already exists");

            if (
                    userInput.getName() != null
                            && userInput.getName().length() > 0
                            && !Objects.equals(user.getName(), userInput.getName())
            ) {
                user.setName(userInput.getName());
            }

            if (
                    userInput.getUsername() != null
                            && userInput.getUsername().length() > 0
                            && !Objects.equals(user.getUsername(), userInput.getUsername())
            ) {
                user.setUsername(userInput.getUsername());
            }

            if (
                    userInput.getImage() != null
                            && userInput.getImage().length() > 0
                            && !Objects.equals(user.getImage(), userInput.getImage())
            ) {
                user.setImage(userInput.getImage());
            }

            if (
                    userInput.getBio() != null
                            && userInput.getBio().length() > 0
                            && !Objects.equals(user.getBio(), userInput.getBio())
            ) {
                user.setBio(userInput.getBio());
            }

            if (
                    userInput.getEmail() != null
                            && userInput.getEmail().length() > 0
                            && !Objects.equals(user.getEmail(), userInput.getEmail())
            ) {
                user.setEmail(userInput.getEmail());
            }

            user.setUpdatedAt(LocalDateTime.now());
            return new Success(userRepository.save(user));
        } catch (PSQLException e) {
            return new Failed(e.getMessage());
        }
    }

    public String deleteUser(Long userId) {
        if (!userRepository.existsById(userId))
            return "User with id: " + userId + " does not exist";

        userRepository.deleteById(userId);
        return "Success";
    }
}
