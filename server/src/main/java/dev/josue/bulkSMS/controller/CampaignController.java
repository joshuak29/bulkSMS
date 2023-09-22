package dev.josue.bulkSMS.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.josue.bulkSMS.entity.Campaign;
import dev.josue.bulkSMS.entity.Message;
import dev.josue.bulkSMS.entity.User;
import dev.josue.bulkSMS.service.CampaignService;
import dev.josue.bulkSMS.service.MessageService;
import dev.josue.bulkSMS.service.UserService;
import dev.josue.bulkSMS.utils.CampaignUtils;
import dev.josue.bulkSMS.utils.UserUtils;

@CrossOrigin()
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    CampaignService service;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    UserUtils userUtils;

    @PostMapping
    public ResponseEntity<Void> addCampaign(@RequestBody HashMap<String, String> data,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            if (!CampaignUtils.isCampaign(data)) {
                throw new IllegalArgumentException("Arguments can't be null");
            }

            Message newMessage = new Message(data.get("textMessage"), data.get("senderId"));
            messageService.createMessage(newMessage);

            Message message = messageService.getMessage(newMessage.getId());
            User user = userUtils.getUser(token);

            Campaign campaign = new Campaign(LocalDateTime.parse(data.get("schedule")), message, user,
                    data.get("numbers"));
            service.createCampaign(campaign);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        return new ResponseEntity<List<Campaign>>(service.getAllCampaigns(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaign(@PathVariable Long id) {
        try {
            Campaign campaign = service.getCampaign(id);

            return new ResponseEntity<Campaign>(campaign, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getNumberofCampaigns() {
        long total = service.numberOfCampaigns();
        return new ResponseEntity<Long>(total, HttpStatus.OK);
    }
}
