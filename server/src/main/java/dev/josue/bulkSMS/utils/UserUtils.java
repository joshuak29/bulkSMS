package dev.josue.bulkSMS.utils;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import dev.josue.bulkSMS.config.JwtService;
import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.repository.UserRepository;
import dev.josue.bulkSMS.service.UserService;

@Service
public class UserUtils {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepo;

    public static Boolean isUser(HashMap<String, String> data) {
        String name = data.get("name");
        String username = data.get("username");
        String password = data.get("password");
        String isAdmin = data.get("isAdmin");

        if (name == null || username == null || password == null || isAdmin == null) {
            return false;
        }

        return true;
    }

    public boolean isAdmin(String token) {
        String authToken = token.substring(7);
        String username = jwtService.extractUsername(authToken);

        User user = userRepo.findByUsername(username).orElseThrow();

        return user.isAdmin();
    }
}
