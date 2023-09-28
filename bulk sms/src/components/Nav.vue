<template>
    <div class="flex flex-col gap-5 bg-white shadow-md">
        <div class="logo text-center h-16 text-2xl font-bold text-blue-500 pt-5">Bulk SMS</div>

        <ul class="p-4 font-semibold text-gray-500 text-xl flex flex-col gap-4">
            <li class="move-on-hover"><router-link :to="{name: 'new'}"><font-awesome-icon icon="fa-solid fa-paper-plane" class="mr-4"/>New SMS</router-link></li>
            <li class="move-on-hover"><router-link :to="{name: 'users'}"><font-awesome-icon icon="fa-solid fa-users" class="mr-4"/>Users</router-link></li>
            <li class="move-on-hover"><router-link :to="{name: 'history'}"><font-awesome-icon icon="fa-solid fa-chart-simple" class="mr-4"/>Dashboard</router-link></li>
            <!-- <li class="move-on-hover"><font-awesome-icon icon="fa-solid fa-chart-simple" class="mr-4"/>Dashboard</li> -->
        </ul>

        <div class="p-4 font-semibold text-blue-500 hover:text-blue-400 text-2xl cursor-pointer" @click="logout">
            Logout <font-awesome-icon icon="right-from-bracket" class="ml-4"/>
        </div>

    </div>
</template>
<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/authStore';

const router = useRouter()
const authStore = useAuthStore();
const logout = () => {
    authStore.logout();
}
</script>
<style scoped>
.move-on-hover {
    @apply hover:translate-x-1 transition-all cursor-pointer
}
.router-link-active {
    @apply text-blue-500 
}
</style>