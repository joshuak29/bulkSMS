<template>
    <div>
        <HeaderVue />
        <router-view>
        </router-view>
    </div>
</template>
<script setup>
import HeaderVue from '@/components/main/Header.vue'
import NewSMSVue from '@/components/main/NewSMS.vue'
</script>
<style scoped>

</style>