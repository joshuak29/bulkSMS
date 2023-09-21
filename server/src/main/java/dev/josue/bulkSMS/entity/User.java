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
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

    @Column(nullable = false)
    private final LocalDate joined = LocalDate.now();

    private LocalDateTime lastLogin;

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

    // Getters and Setters
    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public LocalDate getJoined() {
        return joined;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin() {
        System.out.println("Updated las Login");
        this.lastLogin = LocalDateTime.now();
    }

    public int getCredit() {
        return credit;
    }
    public void incrementCredit(int credit) {
        this.credit += credit;
    }

    public boolean isActive() {
        return isActive;
    }
    public void activate() {
        this.isActive = true;
    }
    public void deactivate() {
        this.isActive = false;
    }

    // hashcode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + (isAdmin ? 1231 : 1237);
        result = prime * result + ((joined == null) ? 0 : joined.hashCode());
        result = prime * result + credit;
        result = prime * result + (isActive ? 1231 : 1237);
        return result;
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (isAdmin != other.isAdmin)
            return false;
        if (joined == null) {
            if (other.joined != null)
                return false;
        } else if (!joined.equals(other.joined))
            return false;
        if (credit != other.credit)
            return false;
        if (isActive != other.isActive)
            return false;
        return true;
    }

    // tostring
    @Override
    public String toString() {
        return "User [Id=" + Id + ", name=" + name + ", username=" + username + ", password=" + password + ", isAdmin="
                + isAdmin + ", joined=" + joined + ", credit=" + credit + ", isActive=" + isActive + "]";
    }
}
