package dev.josue.bulkSMS.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.josue.bulkSMS.repository.UserRepository;

@Configuration
public class ApplicationConfig {

    @Autowired
    UserRepository userRepo;
    
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            }
            
        };
    }
}
