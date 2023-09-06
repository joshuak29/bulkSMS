<template>
    <div class="z-20 fixed left-0 top-0 bg-gray-200/95 w-full h-full flex items-center justify-center">
        <!-- <div class="bg-white rounded-lg flex flex-col gap-6 py-6 px-10"> -->
        <form class="bg-white rounded-lg flex flex-col gap-6 py-6 px-4 w-1/3" @submit.prevent="postUser">
            <input class="input" name="text" required type="text" placeholder="Full Names" v-model="name">
            <input class="input" name="text" required type="text" placeholder="Username" v-model="username">
            <input class="input" name="text" required type="password" placeholder="Password" v-model="password">
            <label class="font-semibold">
                <input type="checkbox" name="isAdmin" id="admin" value="Admin" class="mr-2" v-model="isAdmin">
                Admin</label>
            <div class="flex justify-end gap-4">
                <button id="cancel" @click="$emit('close')">Cancel</button>
                <input type="submit" value="Add">
                <!-- <button id="add" type="submit">Add</button> -->
            </div>
            <!-- <input class="input" name="text" type="text" placeholder="Search the internet..."> -->
        </form>

        <!-- </div> -->
    </div>
</template>
<script setup>
import { ref } from 'vue';

import { useUserStore } from '@/stores/usersStore';

const emits = defineEmits(['close'])

const usersStore = useUserStore();

const name = ref(null);
const username = ref(null);
const password = ref(null);
const isAdmin = ref(false);

const postUser = () => {
    const user = {name: name.value, username: username.value, password: password.value, isAdmin: isAdmin.value}
    usersStore.addUser(user);

    emits('close');
}
</script>
<style scoped>
.input {
    width: 100%;
    max-width: 500px;
    height: 45px;
    padding: 12px;
    border-radius: 12px;
    border: 1.5px solid lightgrey;
    outline: none;
    transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
    box-shadow: 0px 0px 20px -18px;
}

.input:hover {
    border: 2px solid lightgrey;
    box-shadow: 0px 0px 20px -17px;
}

.input:active {
    transform: scale(0.95);
}

.input:focus {
    border: 2px solid grey;
}

button,
input[type='submit'] {
    transition: all 0.2s ease-in-out 0ms;
    user-select: none;
    font-family: 'Poppins', sans-serif;
    @apply w-fit h-8 px-2 py-1 rounded-md border border-gray-300 shadow-sm cursor-pointer bg-white
}

button#cancel {
    @apply bg-red-500
}

input[type='submit'] {
    @apply bg-blue-500 text-white
}

input[type='submit']:hover {
    @apply text-blue-500
}

button:hover,
input[type='submit']:hover {
    background-color: #F2F2F2;
    box-shadow: 0px 0px 20px -18px;
}

button:active,
input[type='submit']:active {
    transform: scale(0.95);
}
</style>