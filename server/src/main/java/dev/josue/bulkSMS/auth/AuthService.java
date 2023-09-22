package dev.josue.bulkSMS.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.josue.bulkSMS.config.JwtService;
import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserRepository userRepo;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder encoder;

    public AuthenticateResponse register(RegisterRequest request) {
        User user = new User(
                request.getName(),
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.isAdmin());

        userRepo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthenticateResponse(token);
    }

    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        System.out.println("hit login");
        final String username = request.getUsername();
        final String password = request.getPassword();

        System.out.println(username);
        System.out.println(password);

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        User user = userRepo.findByUsername(username).orElseThrow();

        System.out.println(user);

        user.setLastLogin(LocalDateTime.now());
        userRepo.save(user);

        System.out.println(user);
        
        String token = jwtService.generateToken(user);

        System.out.println(token);

        return new AuthenticateResponse(token);
    }

    public boolean usernameAvailable(String username) {
        User user = userRepo.findByUsername(username).orElse(null);
        return !(user instanceof User);
    }
}
