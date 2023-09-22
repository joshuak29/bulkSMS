import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import axios from 'axios'
import setAuthHeader from '../utils/setAuthHeader'

export const useCampaignStore = defineStore('campaign', () => {
    const campaigns = ref([]);
    const totalCampaigns = ref(0);

    const getAllCampaigns = async () => {
        campaigns.value = [];
        // setAuthHeader(import.meta.env.VUE_APP_TOKEN);
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        console.log(import.meta.env.MODE);
        console.log(import.meta.env.VUE_APP_TOKEN);
        const results = await axios.get('http://localhost:8080/api/campaigns');
        results.data.forEach(campaign => {
            campaigns.value.push(campaign);
        });
        getTotalCampaigns();
    };

    const getCampaign = async (id) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        const results = await axios.get(`http://localhost:8080/api/campaigns/${id}`);
        return results.data;
    };

    const deleteCampaign = async (id) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        await axios.delete(`http://localhost:8080/api/campaigns/${id}`);
        getAllCampaigns();
    }

    const addCampaign = async (data) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        campaigns.value = [];
        await axios.post('http://localhost:8080/api/campaigns', data);
        campaigns.value = [];
        getAllCampaigns();
    }
    const getTotalCampaigns = async () => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        const results = await axios.get('http://localhost:8080/api/campaigns/total');
        totalCampaigns.value = results.data;
    }

    return { getAllCampaigns, getCampaign, deleteCampaign, addCampaign, getTotalCampaigns, totalCampaigns, campaigns }
})
