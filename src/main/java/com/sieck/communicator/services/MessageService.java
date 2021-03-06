package com.sieck.communicator.services;

import com.sieck.communicator.domain.Message;
import com.sieck.communicator.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements MessageInterface<Message>{

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void send(Message message){ messageRepository.save(message); }

    public List<Message> getAll(){
        return messageRepository.findAll();
    }

    public Message getById(String id) { return messageRepository.findById(id).get(); }

    public void deleteById(String id) { messageRepository.deleteById(id); }
}
