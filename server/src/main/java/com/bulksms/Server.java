package com.bulksms;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bulksms.users.User;
import com.bulksms.users.UserDb;
import com.bulksms.campaigns.Campaign;
import com.bulksms.campaigns.CampaignsDb;


@RestController
public class Server {

    @PostMapping("/users")
    public void addUser(@RequestParam String name, @RequestParam boolean isAdmin, @RequestParam String username, @RequestParam String password) {
        User newUser = new User(name, isAdmin, username, password);
        UserDb.addUser(newUser);
    }

    @GetMapping("/users")
    public ArrayList<User> getUsers() {
        return UserDb.getUsers();
    }

     @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return UserDb.getUser(id);
    }

     @PutMapping("/users/id")
    public void putUser(@PathVariable String id) {
        // Query user with id all details into hashmap and return
    }

     @DeleteMapping("/users/{id}")
    public void deltetUser(@PathVariable int id) {
        UserDb.deleteUser(id);
    }



    @PostMapping("/campaigns")
    public void addCampaign() {
        HashMap<String, Integer> schedule = new HashMap<>();
        schedule.put("year", 2023);
        schedule.put("month", 12);
        schedule.put("date", 202123);

        User user = UserDb.getUser(0);
    }

    @GetMapping("/campaigns")
    public ArrayList<Campaign> getAllCampaigns() {
        return CampaignsDb.getCampaigns();
    }

    @GetMapping("/campaigns/{id}")
    public Campaign getCampaign(@PathVariable int id) {
        return CampaignsDb.getCampaign(id);
    }
}
