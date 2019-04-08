package com.sieck.communicator.services;

import com.sieck.communicator.domain.Message;
import com.sieck.communicator.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService implements ServiceInterface<Message>{

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(Message message){
        Message message1 = new Message(message.getText(), message.getDate(), message.getAuthor());
        return messageRepository.save(message1);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(String id){
        return messageRepository.findById(id).get();
    }

    public void deleteMessageById(String id) { messageRepository.deleteById(id); }
}
