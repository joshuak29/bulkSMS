package dev.josue.bulkSMS.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String username;
    private String password;
    private boolean isAdmin;
}
