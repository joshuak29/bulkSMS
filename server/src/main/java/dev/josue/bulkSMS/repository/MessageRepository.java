package dev.josue.bulkSMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.josue.bulkSMS.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
