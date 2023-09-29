import { ref, computed, nextTick } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router'

import axios from 'axios'
import setAuthHeader from '@/utils/setAuthHeader'

export const useUserStore = defineStore('user', () => {
    const router = useRouter();

    const users = ref([]);
    const getAllUsers = async () => {
        users.value = [];
        setAuthHeader();
        const results = await axios.get('http://localhost:8080/api/users');
        results.data.forEach(user => {
            users.value.push(user);
        });
    };

    const getUser = async (id) => {
        setAuthHeader();
        const results = await axios.get(`http://localhost:8080/api/users/${id}`);
        return results.data;
    };

    const deleteUser = async (id) => {
        setAuthHeader();
        await axios.delete(`http://localhost:8080/api/users/${id}`);
        getAllUsers();
    }

    const addUser = async (data) => {
        setAuthHeader();
        await axios.post('http://localhost:8080/api/users', data);
        getAllUsers();
    }

    const updateUser = async (id, user) => {
        setAuthHeader();
        await axios.put(`http://localhost:8080/api/users/${id}`, {   
            name: user.name,
            username: user.username,
            password: user.password,
            isAdmin: user.isAdmin,
        });
        await nextTick();
        getAllUsers();
        router.push({name: 'users'})
    }

    const searchUsers = async (searchTerm) => {
        setAuthHeader();
        const results = await axios.get(`http://localhost:8080/api/users/search?search=${searchTerm}`);

        if(results.data.length == 0) {
            getAllUsers();
        }else{
            users.value = [];
            results.data.forEach(user => {
                users.value.push(user);
            })
        }
    }

    return { getAllUsers, getUser, deleteUser, addUser, updateUser, searchUsers, users }
})