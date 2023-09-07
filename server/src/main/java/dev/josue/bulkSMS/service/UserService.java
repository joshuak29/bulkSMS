package dev.josue.bulkSMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.repository.UserRepository;

public class UserService {
    
    @Autowired
    UserRepository repo;

    public User addUser(User user) {
        return repo.save(user);
    }

    public User getUser(int id) {
        return repo.getReferenceById(id);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }
}
