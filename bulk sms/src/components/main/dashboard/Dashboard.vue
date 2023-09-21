<template>
    <div class="w-full flex flex-col px-8 py-4 gap-4">
        <!-- stats -->
        <div class="w-full flex flex-row gap-4 justify-evenly items-center py-2">
            <StatVue text="Campaigns" :value="campaignsStore.totalCampaigns"/>
            <StatVue text="Total SMSs" :value="campaignsStore.totalMessages"/>
            <StatVue text="Numbers Reached" value="214"/>
            <StatVue text="Pending Campaigns" value="4"/>
        </div>
        <!-- heading and header -->
        <div class="w-full flex flex-row justify-end items-center h-12">
            <!-- add SMS button  -->
            <div><router-link :to="{ name: 'new' }"><button
                        class="px-4 font-semibold text-blue-500 rounded-lg  relative w-36 h-10 cursor-pointer flex items-center overflow-x-hidden border border-blue-100 bg-blue-50 group hover:bg-blue-200 active:bg-blue-200 active:border-blue-200">

                        New
                        SMS
                        <span
                            class="absolute right-0 h-full w-10 rounded-lg bg-blue-400 flex items-center justify-center transform group-hover:translate-x-0 group-hover:w-full transition-all duration-300">
                            <svg class="svg w-8 text-white" fill="none" height="24" stroke="currentColor"
                                stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
                                width="24" xmlns="http://www.w3.org/2000/svg">
                                <line x1="12" x2="12" y1="5" y2="19"></line>
                                <line x1="5" x2="19" y1="12" y2="12"></line>
                            </svg>
                        </span>
                    </button></router-link></div>
        </div>
        <!-- table -->
        <div class="w-full bg-white p-4 rounded-xl shadow-md">
            <table class="w-full">
                <thead class="h-14 border-b">
                    <th>#</th>
                    <th class="text-left">Subject</th>
                    <th class="text-left">Sender ID</th>
                    <th class="text-left">Target</th>
                    <th class="text-left">Schedule</th>
                    <th class="text-left">Status</th>
                    <th class="text-left">Sender</th>
                </thead>
                <tbody>
                    <tr v-for="campaign in campaignsStore.campaigns" :key="campaign.id">
                        <th>{{ campaign.id }}</th>
                        <td>{{ campaign.message.text}}</td>
                        <td>{{ campaign.message.senderId }}</td>
                        <td>MTN MOMO</td>
                        <td>{{ campaign.schedule}}</td>
                        <td>Sent</td>
                        <td>{{ campaign.user.username }}</td>
                        <td><font-awesome-icon icon="pen-to-square" @click="edit"></font-awesome-icon></td>
                    </tr>

                </tbody>

            </table>
        </div>
    </div>
</template>
<script setup>
import { useRouter } from 'vue-router';
import { onBeforeMount } from 'vue';

import { useCampaignStore } from '@/stores/CampaignsStore.js'

import StatVue from '@/components/main/dashboard/Stat.vue'

const router = useRouter()

const campaignsStore = useCampaignStore();

const edit = () => {
    router.push({name: "new"})
}

onBeforeMount(() => {
    campaignsStore.getAllCampaigns();
})
</script>
<style scoped>
thead th {
    @apply text-blue-400 font-semibold
}

tbody tr {
    @apply h-8
}

tbody tr {
    @apply hover:bg-gray-100 cursor-pointer
}

td svg {
    @apply text-gray-700 hover:text-gray-600
}
</style>