package dev.josue.bulkSMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.josue.bulkSMS.entity.Campaign;
import dev.josue.bulkSMS.repository.CampaignRepository;

@Service
public class CampaignService {
    
    @Autowired 
    CampaignRepository repo;

    public Campaign createCampaign(Campaign campaign) {
        return repo.save(campaign);
    }

    public Campaign getCampaign(int id) {
        return repo.getReferenceById(id);
    }

    public List<Campaign> getAllCampaigns() {
        return repo.findAll();
    }

    public void deleteCampaign(int id) {
        repo.deleteById(id);
    }
    
    public long numberOfCampaigns() {
        return repo.count();
    }
}
