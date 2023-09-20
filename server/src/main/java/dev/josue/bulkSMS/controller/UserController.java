package dev.josue.bulkSMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    UserUtils userUtils;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody HashMap<String, String> data) {
        if (userUtils.isAdmin(token)) {
            try {
                if (!UserUtils.isUser(data)) {
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
        } else {
            System.out.println("Unauthorized");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return new ResponseEntity<List<User>>(service.getAllUsers(), HttpStatus.OK);
        // if (userUtils.isAdmin(token)) {
        //     return new ResponseEntity<List<User>>(service.getAllUsers(), HttpStatus.OK);
        // } else {
        //     System.out.println("Unauthorized");
        //     return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        // }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id) {
        if (userUtils.isAdmin(token)) {
            try {
                User user = service.getUser(id);
                System.out.println(user);
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            System.out.println("Unauthorized");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id,
            @RequestBody HashMap<String, String> data) {
        if (userUtils.isAdmin(token)) {
            try {
                boolean isAdminBool = Boolean.parseBoolean(data.get("isAdmin"));

                User user = service.getUser(id);
                System.out.println(user);

                if (!UserUtils.isUser(data)) {
                    throw new IllegalArgumentException("Arguments can't be null");
                }

                User newUser = new User(data.get("name"), data.get("username"), data.get("password"), isAdminBool);

                service.updateUser(id, newUser);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e);

                if (e instanceof IllegalArgumentException) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } else {
            System.out.println("Unauthorized");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletetUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable Long id) {
        if (userUtils.isAdmin(token)) {
            try {
                User user = service.getUser(id);
                System.out.println(user);

                service.deleteUser(id);
                return new ResponseEntity<>(HttpStatus.OK);

            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            System.out.println("Unauthorized");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object[]> getUsersSearch(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestParam String search) {
        if (userUtils.isAdmin(token)) {
            return new ResponseEntity<>(service.getByName(search), HttpStatus.OK);
        } else {
            System.out.println("Unauthorized");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
