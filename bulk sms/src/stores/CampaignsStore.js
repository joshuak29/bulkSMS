import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import axios from 'axios'

export const useCampaignStore = defineStore('campaign', () => {
    const campaigns = ref([]);

    const getAllCampaigns = async () => {
        campaigns.value = [];
        const results = await axios.get('http://localhost:8080/api/campaigns');
        results.data.forEach(campaign => {
            campaigns.value.push(campaign);
        });
    };

    const getCampaign = async (id) => {
        const results = await axios.get(`http://localhost:8080/api/campaigns/${id}`);
        return results.data;
    };

    const deleteCampaign = async (id) => {
        await axios.delete(`http://localhost:8080/api/campaigns/${id}`);
        getAllCampaigns();
    }

    const addCampaign = async (data) => {
        await axios.post('http://localhost:8080/api/campaigns', data);
        campaigns.value = [];
        getAllCampaigns();
    }

    return { getAllCampaigns, getCampaign, deleteCampaign, addCampaign, campaigns }
})
