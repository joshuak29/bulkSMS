package dev.josue.bulkSMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.service.UserService;
import dev.josue.bulkSMS.utils.UserUtils;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody HashMap<String, String> data) {
        // System.out.println(data);
        try {
            if(! UserUtils.isUser(data)) {
                throw new IllegalArgumentException("Arguments can't be null");
            }

            boolean isAdminBool = Boolean.parseBoolean(data.get("isAdmin"));

            User user = new User(data.get("name"), data.get("username"), data.get("password"), isAdminBool);
            service.addUser(user);
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        try {
            User user = service.getUser(id);
            System.out.println(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putUser(@PathVariable Long id, @RequestBody HashMap<String, String> data) {
        try {
            boolean isAdminBool = Boolean.parseBoolean(data.get("isAdmin"));

            User user = service.getUser(id);
            System.out.println(user);

            if(!UserUtils.isUser(data)) {
                throw new IllegalArgumentException("Arguments can't be null");
            }

            User newUser = new User(data.get("name"), data.get("username"), data.get("password"), isAdminBool);

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletetUser(@PathVariable Long id) {
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

    @GetMapping("/search")
    public ResponseEntity<Object[]> getUsersSearch(@RequestParam String search) {
        return new ResponseEntity<>(service.getByName(search), HttpStatus.OK);
    }
}
