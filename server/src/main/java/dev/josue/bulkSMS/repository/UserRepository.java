package dev.josue.bulkSMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.josue.bulkSMS.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    public List<User> findByName(String name);
    // public List<User> findbyUsername(String Username);
}
