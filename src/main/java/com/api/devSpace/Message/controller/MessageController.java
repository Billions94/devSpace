package com.api.devSpace.Message.controller;

import com.api.devSpace.Message.entity.Message;
import com.api.devSpace.Message.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MessageController {
    @Autowired
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/message")
    @SendTo("/space/public")
    public Message receiveSpaceMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {
        messageService.saveMessage(message, null);
        return message;
    }
}
