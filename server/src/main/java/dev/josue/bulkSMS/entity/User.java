package dev.josue.bulkSMS.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

@Data
@Getter 
@Setter
@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    private LocalDateTime lastLogin;

    @Column(nullable = false)
    private final LocalDate joined = LocalDate.now();

    @Column(nullable = false)
    private int credit;

    @Column(nullable = false)
    private boolean isActive;

    // Constructors
    public User() {}
    public User(String name, String username, String password, boolean isAdmin) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.credit = 10_000;
        this.isActive = true;
    }

    // 
    public void incrementCredit(int value) {
        this.credit += value;
    }

    // Override methods
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("User"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
