package dev.josue.bulkSMS.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.josue.bulkSMS.entity.Campaign;
import dev.josue.bulkSMS.entity.Message;
import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.service.CampaignService;
import dev.josue.bulkSMS.service.MessageService;
import dev.josue.bulkSMS.service.UserService;

@CrossOrigin()
@RestController
public class CampaignController {

    @Autowired
    CampaignService service;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @PostMapping("/api/campaigns")
    public void addCampaign(@RequestBody Map<String, String> data) {
        String senderId = data.get("senderId");
        String text = data.get("textMessage");
        String schedule = data.get("schedule");

        Message newMessage = new Message(text, senderId);
        messageService.createMessage(newMessage); 
        
        Message message = messageService.getMessage(newMessage.getId());
        User user = userService.getUser(1);

        Campaign campaign = new Campaign(LocalDateTime.parse(schedule), message, user);
        service.createCampaign(campaign);
    }

    @GetMapping("/api/campaigns")
    public List<Campaign> getAllCampaigns() {
        return service.getAllCampaigns();
    }

    @GetMapping("/api/campaigns/{id}")
    public Campaign getCampaign(@PathVariable int id) {
        return service.getCampaign(id);
    }
}
