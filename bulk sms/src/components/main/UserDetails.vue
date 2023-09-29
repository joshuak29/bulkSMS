<template>
    <div class="w-1/2 flex flex-col px-8 py-4 gap-12">
        <!-- heading -->
        <div class="text-blue-500 font-bold text-2xl">
            <font-awesome-icon icon="arrow-left" class="text-gray-900 mr-4 font-normal hover:text-gray-600"
                @click="$emit('close')" />
            Edit Profile
        </div>

        <form @submit.prevent="updateUser" class="flex flex-col gap-4 ml-6">
            <input class="input w-1/2" name="text" required type="text" placeholder="Full Names" v-model="newName">
            <input class="input w-1/2" name="text" required type="text" placeholder="Username" v-model="newUsername">

            <!-- new password and confirm -->
            <input class="input w-1/2" name="text" type="password" placeholder="New password"
                :required="newPassword != null || confirmNewPassword != null" v-model="newPassword">
            <input class="input w-1/2" name="text" type="password" placeholder="Confirm New password"
                :required="newPassword != null || confirmNewPassword != null" v-model="confirmNewPassword">

            <!-- error -->
            <div class="-mt-3" v-if="!passwordMatch">
                <span><font-awesome-icon icon="circle-exclamation" class="text-red-500 mr-2 animate-pulse" /></span>
                <span class="text-red-500 font-light text-xs">Passwords dont match!!</span>
            </div>

            <!-- admin checkbox -->
            <label class="font-semibold mb-4">
                <input type="checkbox" name="isAdmin" id="admin" value="Admin" class="mr-2" v-model="newIsAdmin">
                Admin</label>

            <!-- buttons -->
            <div class="flex justify-end gap-4">
                <input type="submit" value="Update">
                <button type="button" class="bg-red-500" @click="deactivateUser()">Deactivate</button>
            </div>
        </form>

    </div>
</template>
<script setup>
import { ref, computed } from 'vue';

import { useUserStore } from '@/stores/usersStore.js';

const props = defineProps(['user'])

const userStore = useUserStore();

const newName = ref(props.user.name);
const newUsername = ref(props.user.username);
const newPassword = ref();
const confirmNewPassword = ref();
const newIsAdmin = ref(props.user.admin);

const passwordMatch = computed(() => {
    if (newPassword.value || confirmNewPassword.value) {
        return newPassword.value == confirmNewPassword.value;
    }
    return true;
})

const newPasswordConfirm = () => {
    if (newPassword.value) {
        if (passwordMatch) {
            return newPassword.value;
        } else {
            return null;
        }
    } else {
        return null;
    }
}

const updateUser = (id, user) => {
    userStore.updateUser(props.user.id, {
        name: newName.value,
        username: newUsername.value,
        password: newPasswordConfirm() || props.user.password,
        isAdmin: newIsAdmin.value.toString()
    })
}

const emits = defineEmits(['close'])

const deactivateUser = () => {
    userStore.deleteUser(1);
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
    @apply w-fit h-8 px-2 py-1 rounded-md border border-gray-300 shadow-sm cursor-pointer bg-red-500 text-white
}

input[type='submit'] {
    @apply bg-green-500 text-white px-6
}

input[type='submit']:hover {
    @apply text-blue-500
}

button:hover,
input[type='submit']:hover {
    background-color: #F2F2F2;
    box-shadow: 0px 0px 20px -18px;
    @apply text-blue-400
}

button:active,
input[type='submit']:active {
    transform: scale(0.95);
}
</style>