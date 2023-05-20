package com.api.devSpace.Message.service;

import com.api.devSpace.Message.entity.Message;
import com.api.devSpace.Message.repository.MessageRepository;
import com.api.devSpace.space.Entity.Space;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService implements MessageServiceInterface {
    @Autowired
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageRepository messageRepository;

    public void saveMessage(Message message, @Nullable Space space) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        message.setCreatedAt(LocalDateTime.now());
        messageRepository.save(message);
    }

    public List<Message> getMessages(Long id, Long rId) {
        return messageRepository.findAll();
    }
}
