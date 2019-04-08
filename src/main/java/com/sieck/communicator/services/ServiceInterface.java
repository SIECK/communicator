package com.sieck.communicator.services;

import com.sieck.communicator.domain.Message;

import java.util.List;

public interface ServiceInterface<T> {
    Message sendMessage(T obj);
    List<T> getAllMessages();
    T getMessageById(String id);
    void deleteMessageById(String id);
}
