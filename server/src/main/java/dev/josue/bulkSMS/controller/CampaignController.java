package dev.josue.bulkSMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.josue.bulkSMS.entity.Campaign;
import dev.josue.bulkSMS.service.CampaignService;

@RestController
@CrossOrigin
public class CampaignController {
    @Autowired
    CampaignService service;

    @PostMapping("/api/campaigns")
    public void addCampaign(@RequestBody Map<String, String> data) {
        String senderId = data.get("senderId");
        String text = data.get("textMessage");

        System.out.println(data);

        CampaignsDb.addCampaign(senderId, text);
    }

    @GetMapping("/api/campaigns")
    public List<Campaign> getAllCampaigns() {
        return CampaignsDb.getCampaigns();
    }

    @GetMapping("/api/campaigns/{id}")
    public Campaign getCampaign(@PathVariable int id) {
        return CampaignsDb.getCampaign(id);
    }

    @GetMapping("/api/test/message")
    public Message getMessage() {
        return new Message("Asake in rwanda", "BK Arena");
    }
}
