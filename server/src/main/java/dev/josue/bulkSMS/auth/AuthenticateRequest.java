package dev.josue.bulkSMS.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequest {
    String username;
    String password;
}
