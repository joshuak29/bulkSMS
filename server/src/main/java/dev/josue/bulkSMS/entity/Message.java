package dev.josue.bulkSMS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private String text;

    @Column
    private String senderId;

    @OneToOne
    @JoinColumn(name = "campaignId")
    private Campaign campaign;
    
    public Message() {}
    public Message(String text, String senderId) {
        this.text = text;
        this.senderId = senderId;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return text;
    }
    public void setName(String text) {
        this.text = text;
    }

    public String getUsername() {
        return senderId;
    }
    public void setUsername(String senderId) {
        this.senderId = senderId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Id;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((senderId == null) ? 0 : senderId.hashCode());
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
        Message other = (Message) obj;
        if (Id != other.Id)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (senderId == null) {
            if (other.senderId != null)
                return false;
        } else if (!senderId.equals(other.senderId))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Message [Id=" + Id + ", text=" + text + ", senderId=" + senderId + "]";
    }

    
}
