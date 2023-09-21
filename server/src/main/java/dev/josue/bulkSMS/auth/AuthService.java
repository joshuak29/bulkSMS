package dev.josue.bulkSMS.auth;

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
        final String username = request.getUsername();
        final String password = request.getPassword();

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        User user = userRepo.findByUsername(username).orElseThrow();
        user.setLastLogin();

        userRepo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthenticateResponse(token);
    }

    public boolean usernameAvailable(String username) {
        User user = userRepo.findByUsername(username).orElse(null);
        System.out.println(user);
        System.out.println(user instanceof User);
        return !(user instanceof User);
    }
}
