package com.bulksms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bulksms.users.User;
import com.bulksms.users.UserDb;
import com.bulksms.campaigns.Campaign;
import com.bulksms.campaigns.CampaignsDb;


@RestController
@CrossOrigin
public class Server {
    @PostMapping("/api/users")
    public void addUser(@RequestBody User user) {
        UserDb.addUser(user);
    }

    @GetMapping("/api/users")
    public ArrayList<User> getUsers() {
        return UserDb.getUsers();
    }

     @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable int id) {
        return UserDb.getUser(id);
    }

     @PutMapping("/api/users/{id}")
    public void putUser(@PathVariable String id) {
        // Query user with id all details into hashmap and return
    }

     @DeleteMapping("/api/users/{id}")
    public void deltetUser(@PathVariable int id) {
        UserDb.deleteUser(id);
    }



    @PostMapping("/api/campaigns")
    public void addCampaign() {
        CampaignsDb.addCampaign();
    }

    @GetMapping("/api/campaigns")
    public ArrayList<Campaign> getAllCampaigns() {
        return CampaignsDb.getCampaigns();
    }

    @GetMapping("/api/campaigns/{id}")
    public Campaign getCampaign(@PathVariable int id) {
        return CampaignsDb.getCampaign(id);
    }
}
