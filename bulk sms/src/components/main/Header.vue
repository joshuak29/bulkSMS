<template>
    <div class="w-full h-16 px-4 py-1 bg-blue-500 text-white flex flex-row gap-4 items-center justify-end shadow-md">
        <!-- name and role -->
        <div class="flex flex-col h-full">
            <div class="font-semibold" v-if="authStore.user">{{ authStore.user.name}}  ( {{ authStore.user.username }} )</div>
            <div class="font-semibold" v-else>Loading...</div>
            <div class="text-gray-300 text-xs text-right" v-if="authStore.isAdmin()">Admin</div>
            <div class="text-gray-300 text-xs text-right" v-else>Agent</div>
        </div>

        <!-- profile pic -->
        <div class="h-full w-14 bg-gray-400 rounded-full"></div>
    </div>
</template>
<script setup>
import { computed, onMounted } from 'vue'
import { useAuthStore } from '@/stores/authStore';

const authStore = useAuthStore();

onMounted(() => {
    console.log(authStore.user)
})
const username = computed(() => {
    return authStore.user
})

// {username}
</script>
<style scoped>
    
</style>