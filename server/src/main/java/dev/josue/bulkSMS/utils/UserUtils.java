package dev.josue.bulkSMS.utils;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.service.UserService;

public class UserUtils {
    @Autowired
    UserService service;

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
}
