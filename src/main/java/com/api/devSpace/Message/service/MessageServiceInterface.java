package com.api.devSpace.Message.service;

import com.api.devSpace.Message.entity.Message;
import com.api.devSpace.space.Entity.Space;
import jakarta.annotation.Nullable;

public interface MessageServiceInterface {
    void saveMessage(Message message, @Nullable Space space);
}
