<template>
    <div class="w-1/4 h-full rounded-md shadow-md bg-white p-4 flex flex-row justify-between items-center px-6">
        <span class="font-semibold font-xl">{{ props.text }}</span>
        <span class="font-bold text-green-400 text-3xl">{{ props.value }}</span>
    </div>
</template>
<script setup>
const props = defineProps({
    text: {
        type: String,
        required: true
    },
    value: {
        type: Number,
        required: true
    }
})
</script>
<style>
    
</style>