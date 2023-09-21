import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import axios, { AxiosHeaders } from 'axios'

import setAuthHeader from '@/utils/setAuthHeader';

// axios.defaults.headers.common['Authorization'] = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3N1ZSIsImlhdCI6MTY5NTIwNDg1OCwiZXhwIjoxNjk1MjkxMjU4fQ.78psrlE0FvKlPCDX7TIQuSKu8sG6_ziovc_jvf50bPM';

export const useCampaignStore = defineStore('campaign', () => {
    const campaigns = ref([]);
    const totalCampaigns = ref(0);
    const totalMessages = ref(0);

    const getAllCampaigns = async () => {
        campaigns.value = [];
        // await axios.get('http://localhost:8080/api/campaigns/test');
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3N1ZSIsImlhdCI6MTY5NTI5NjY1MCwiZXhwIjoxNjk1MzgzMDUwfQ.WjwLdyYk5fuulO-mUfNJ8ukbFCH5aicLIAsC-wOpbyc");
        const results = await axios.get('http://localhost:8080/api/campaigns');
        results.data.forEach(campaign => {
            campaigns.value.push(campaign);
        });
        getTotalCampaigns();
        getTotalMessages();
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
        campaigns.value = [];
        await axios.post('http://localhost:8080/api/campaigns', data);
        campaigns.value = [];
        getAllCampaigns();
    }
    const getTotalCampaigns = async () => {
        const results = await axios.get('http://localhost:8080/api/campaigns/total');
        totalCampaigns.value = results.data;
    }
    const getTotalMessages = async () => {
        const results = await axios.get('http://localhost:8080/api/campaigns/messages/total');
        totalMessages.value = results.data;
    }

    return { getAllCampaigns, getCampaign, deleteCampaign, addCampaign, getTotalCampaigns, getTotalMessages, totalMessages, totalCampaigns, campaigns }
})
