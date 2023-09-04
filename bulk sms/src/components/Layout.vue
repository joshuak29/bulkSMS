<template>
    <div id="layout">
        <NavVue />
        <MainVue />
    </div>
</template>
<script setup>
import NavVue from '@/components/Nav.vue'
import MainVue from '@/components/Main.vue';

</script>
<style scoped>
#layout {
    display: grid;
    grid-template-columns: 15vw auto;
    grid-template-rows: 100vh;
}
</style>