import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import axios from 'axios'

export const useUserStore = defineStore('user', () => {
    const users = ref([]);
    const getAllUsers = async () => {
        users.value = [];
        const results = await axios.get('http://localhost:8080/api/users');
        results.data.forEach(user => {
            users.value.push(user);
        });
    };

    const getUser = async (id) => {
        const results = await axios.get(`http://localhost:8080/api/users/${id}`);
        return results.data;
    };

    const deleteUser = async (id) => {
        await axios.delete(`http://localhost:8080/api/users/${id}`);
        getAllUsers();
    }

    const addUser = async (data) => {
        await axios.post('http://localhost:8080/api/users', data);
        getAllUsers();
    }

    return { getAllUsers, getUser, deleteUser, addUser, users }
})