package com.api.devSpace.user.repository;

import com.api.devSpace.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String  name);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserById(Long userId);
}
