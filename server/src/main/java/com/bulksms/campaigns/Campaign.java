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

    // public double calculatePrice() {
    //     double tot = this.message.getPrice();

    //     return tot;
    // }

    // TODO: save to db campaign

    // public static void main(String[] args) {
    //     Message sms = new Message("I am joshua", "JOSHUA");

    //     User user = new User(
    //             "Joshua",
    //             "KG 17 s",
    //             "0790956183",
    //             "12003",
    //             "email@example.com",
    //             false);

    //     HashMap<String, Integer> dict = new HashMap<String, Integer>();

    //     dict.put("year", 2003);
    //     dict.put("month", 4);
    //     dict.put("date", 13);

        // Campaign campaign = new Campaign(sms, dict, user);

        // System.out.println(campaign.calculatePrice());

    // }
}
