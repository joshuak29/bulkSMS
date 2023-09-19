package dev.josue.bulkSMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.josue.bulkSMS.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public List<User> findByName(String name);
    public Optional<User> findByUsername(String username);
    // public List<User> findbyUsername(String Username);
}
