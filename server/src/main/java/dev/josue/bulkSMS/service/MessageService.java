package dev.josue.bulkSMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.josue.bulkSMS.entity.Message;
import dev.josue.bulkSMS.repository.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    MessageRepository repo;

    public Message createMessage(Message message) {
        return repo.save(message);
    }
    
    public Message getMessage(int id) {
        return repo.getReferenceById(id);
    }
}
