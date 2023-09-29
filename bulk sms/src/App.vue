<script setup>
import { RouterLink, RouterView } from 'vue-router'
import LayoutVue from '@/components/Layout.vue';
import { onBeforeMount } from 'vue'

import { useAuthStore } from '@/stores/authStore';

const authStore = useAuthStore();

onBeforeMount(() => {
  authStore.getUser();
})
</script>

<template>
  <div class="w-full h-screen">
    <LayoutVue/>
  </div>
</template>

<style>
:root {
  padding: 0;
  margin: 0;
  @apply bg-gray-100
}
</style>
