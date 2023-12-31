package dev.josue.bulkSMS.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repo;

    public User addUser(User user) {
        return repo.save(user);
    }

    public User getUser(Long id) {
        return repo.getReferenceById(id);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Object[] getByName(String name) {
        List<User> byName =  repo.findByName(name);
        // List<User> byUname = repo.findbyUsername(name);

        Set<User> users = new HashSet<User>();

        users.addAll(byName);
        // users.addAll(byUname);

        return users.toArray();
    }

    public User updateUser(Long id, User newUser) {
        User user = repo.getReferenceById(id);

        user.setName(newUser.getName());
        user.setAdmin(newUser.isAdmin());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());

        return repo.save(user);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    public void addCredit(int amount, Long id) {
        User user = repo.getReferenceById(id);
        user.incrementCredit(amount);
    }
}
