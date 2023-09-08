package dev.josue.bulkSMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.service.UserService;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/api/users")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable int id) {
        return service.getUser(id);
    }

    @CrossOrigin
    @PutMapping("/api/users/{id}")
    public User putUser(@PathVariable int id, @RequestBody User newUser) {
        // Query user with id all details into hashmap and return
        return service.updateUser(id, newUser);
    }

    @DeleteMapping("/api/users/{id}")
    public void deletetUser(@PathVariable int id) {
        service.deleteUser(id);
    }
}
