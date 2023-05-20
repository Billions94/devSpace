package com.api.devSpace.Message.repository;

import com.api.devSpace.Message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
