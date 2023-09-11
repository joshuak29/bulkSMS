package dev.josue.bulkSMS.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repo;

    public User addUser(User user) {
        LocalDate date = LocalDate.now();
        return repo.save(user);
    }

    public User getUser(int id) {
        return repo.getReferenceById(id);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User updateUser(int id, User newUser) {
        User user = repo.getReferenceById(id);

        user.setName(newUser.getName());
        user.setIsAdmin(newUser.isAdmin());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());

        return repo.save(user);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public void addCredit(int amount, int id) {
        User user = repo.getReferenceById(id);
        user.incrementCredit(amount);
    }
}
