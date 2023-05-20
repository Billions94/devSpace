package com.api.devSpace.user.repository;

import com.api.devSpace.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByName(String  name);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserById(Long userId);
}
