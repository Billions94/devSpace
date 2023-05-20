package com.api.devSpace.Message.input;

import com.api.devSpace.Message.enums.Status;
import lombok.Data;

@Data
public class MessageInput {
    private String sender;
    private String receiver;
    private Status status;
}
