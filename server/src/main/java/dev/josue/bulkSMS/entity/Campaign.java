package dev.josue.bulkSMS.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.type.descriptor.jdbc.JsonJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Campaign {
    
    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false)
    private LocalDateTime schedule;

    @Column(nullable = false)
    private String numbers;

    @OneToOne
    @JoinColumn(name = "messageId")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Campaign() {}
    public Campaign(LocalDateTime schedule, Message message, User user, String numbers) {
        this.schedule = schedule;
        this.message = message;
        this.user = user;
        this.numbers = numbers;
    }

    public Long getId() {
        return Id;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }
    // public void setSchedule(LocalDateTime schedule, int messageId, int userId) {
    //     this.schedule = schedule;
    // }

    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumbers() {
        return numbers;
    }
    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
        result = prime * result + ((numbers == null) ? 0 : numbers.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Campaign other = (Campaign) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        if (numbers == null) {
            if (other.numbers != null)
                return false;
        } else if (!numbers.equals(other.numbers))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Campaign [Id=" + Id + ", schedule=" + schedule + ", message=" + message.getText() + ", user=" + user.getUsername() + "]";
    }
}
