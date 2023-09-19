package dev.josue.bulkSMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.josue.bulkSMS.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
}
