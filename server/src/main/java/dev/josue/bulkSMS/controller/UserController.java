package dev.josue.bulkSMS.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import dev.josue.bulkSMS.utils.UserUtils;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/api/users")
    public ResponseEntity<String> addUser(@RequestBody HashMap<String, String> data) {
        // System.out.println(data);
        try {
            String name = data.get("name");
            String username = data.get("username");
            String password = data.get("password");
            String isAdmin = data.get("isAdmin");

            // if (name == null || username == null || password == null || isAdmin == null) {
            //     throw new IllegalArgumentException("Arguments can't be null");
            // }
            if(! UserUtils.isUser(data)) {
                throw new IllegalArgumentException("Arguments can't be null");
            }

            boolean isAdminBool = Boolean.parseBoolean(isAdmin);

            User user = new User(name, username, password, isAdminBool);
            service.addUser(user);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        try {
            User user = service.getUser(id);
            System.out.println(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<Void> putUser(@PathVariable int id, @RequestBody HashMap<String, String> data) {
        try {
            String name = data.get("name");
            String username = data.get("username");
            String password = data.get("password");
            String isAdmin = data.get("isAdmin");

            boolean isAdminBool = Boolean.parseBoolean(isAdmin);

            User user = service.getUser(id);
            System.out.println(user);

            if(!UserUtils.isUser(data)) {
                throw new IllegalArgumentException("Arguments can't be null");
            }

            User newUser = new User(name, username, password, isAdminBool);

            service.updateUser(id, newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);

            if(e instanceof IllegalArgumentException) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deletetUser(@PathVariable int id) {
        try {
            User user = service.getUser(id);
            System.out.println(user);

            service.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
