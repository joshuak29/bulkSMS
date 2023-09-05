package com.bulksms.campaigns;

import java.time.*;
import java.util.HashMap;

import com.bulksms.Message;
import com.bulksms.users.User;

public class Campaign {
    private Message message;
    public String[] numbers;
    private LocalDate schedule;
    private final User sender;
    private final double price;

    // constructor
    public Campaign(String senderId, String textMessage, String[] numbers, HashMap<String, Integer> schedule, User sender) {
        this.message = new Message(textMessage, senderId);
        // TODO: save new message to db

        this.sender = sender;
        this.price = this.message.getPrice();

        try {
            this.schedule = LocalDate.of(schedule.get("year"), schedule.get("month"), schedule.get("date"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.schedule = LocalDate.now();
        }
    }

    public void setSchedule(String newTime) {
        LocalDate newSchedule = LocalDate.parse(newTime);
        this.schedule = newSchedule;
    }

    public LocalDate getSchedule() {
        return this.schedule;
    }
}
