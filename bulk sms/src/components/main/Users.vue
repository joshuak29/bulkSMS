<template>
    <div class="w-full">
        <teleport to='#app'>
            <NewUserVue @close="addUserModalOpen = false" v-if="addUserModalOpen"/>
        </teleport>
        <div class="w-full flex flex-col px-8 py-4 gap-12" v-if="!userDetailsOpen">
            <!-- heading and header -->
            <div class="w-full flex flex-row justify-between items-center h-16">
                <div class="font-bold text-3xl text-blue-400">User Management</div>
                <div class="h-full items-center flex justify-end gap-10">
                    <!-- search user -->
                    <form @submit.prevent="searchUsers"
                        class="inputBox_container flex items-center w-fit h-fit bg-blue-200 rounded-xl overflow-hidden max-w-xs">
                        <svg class="search_icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48" alt="search icon">
                            <path
                                d="M46.599 46.599a4.498 4.498 0 0 1-6.363 0l-7.941-7.941C29.028 40.749 25.167 42 21 42 9.402 42 0 32.598 0 21S9.402 0 21 0s21 9.402 21 21c0 4.167-1.251 8.028-3.342 11.295l7.941 7.941a4.498 4.498 0 0 1 0 6.363zM21 6C12.717 6 6 12.714 6 21s6.717 15 15 15c8.286 0 15-6.714 15-15S29.286 6 21 6z">
                            </path>
                        </svg>
                        <input class="inputBox" id="inputBox" type="text" placeholder="Search User" v-model="searchTerm">
                    </form>

                    <!-- add user button  -->
                    <div><button
                            class="rounded-lg  relative w-36 h-10 cursor-pointer flex items-center overflow-x-hidden border border-blue-100 bg-blue-50 group hover:bg-blue-200 active:bg-blue-200 active:border-blue-200">
                            <span
                                class="text-blue-500 font-semibold ml-8 transform group-hover:translate-x-20 transition-all duration-300">Add
                                User</span>
                            <span
                                @click="addUserModalOpen = true"
                                class="absolute right-0 h-full w-10 rounded-lg bg-blue-400 flex items-center justify-center transform group-hover:translate-x-0 group-hover:w-full transition-all duration-300">
                                <svg class="svg w-8 text-white" fill="none" height="24" stroke="currentColor"
                                    stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
                                    width="24" xmlns="http://www.w3.org/2000/svg">
                                    <line x1="12" x2="12" y1="5" y2="19"></line>
                                    <line x1="5" x2="19" y1="12" y2="12"></line>
                                </svg>
                            </span>
                        </button></div>
                </div>
            </div>

            <!-- table  -->
            <div class="w-full bg-white p-4 rounded-xl shadow-md">
                <table class="w-full">
                    <thead class="h-14 border-b">
                        <th>#</th>
                        <th class="text-left">Name</th>
                        <th class="text-left">Role</th>
                        <th class="text-left">Joined</th>
                        <th class="text-left">Last Login</th>
                        <th class="text-left">Status</th>
                    </thead>
                    <tbody>
                        <tr v-for="(user, i) in usersStore.users" :key="i">
                            <th>{{ user.id }}</th>
                            <td>{{ user.name}}</td>
                            <td v-if="user.admin">Admin</td>
                            <td v-else>Agent</td>
                            <td>{{ user.joined }}</td>
                            <td>12-07-2023 12:00</td>
                            <td>Active</td>
                            <td><font-awesome-icon icon="gear" @click="openUserDetails(user)"></font-awesome-icon></td>
                        </tr>
                    </tbody>

                </table>
                <!-- <div class="w-full flex flex-row items-center justify-between px-4 mt-4 text-sm text-gray-500">
                    <div class="text-xs">Show 5 out of 21</div>
                    <div>Joshua</div>
                </div> -->
            </div>
        </div>

        <UserDetailsVue @close="userDetailsOpen=false" :user="currentUser" v-else/>
    </div>
</template>
<script setup>
import { ref } from 'vue'
import { onBeforeMount } from 'vue';

import { useUserStore } from '@/stores/usersStore';
import NewUserVue from '@/components/main/NewUser.vue'
import UserDetailsVue from '@/components/main/UserDetails.vue';

const userDetailsOpen = ref(false);

const usersStore = useUserStore();
onBeforeMount(() => {
  usersStore.getAllUsers();
})

const searchTerm = ref();
const searchUsers = () => {
    usersStore.searchUsers(searchTerm.value)
}

const addUserModalOpen = ref(false)

const currentUser = ref();

const openUserDetails = (data) => {
    currentUser.value = data;
    userDetailsOpen.value = true;
}
</script>
<style scoped>
.search_icon {
    height: 1em;
    padding: 0 0.5em 0 0.8em;
    fill: #abb2bf;
}

.inputBox {
    background-color: transparent;
    color: #ffffff;
    outline: none;
    width: 100%;
    border: 0;
    padding: 0.5em 1.5em 0.5em 0;
    font-size: 1em;
}

::placeholder {
    color: #abb2bf;
}

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