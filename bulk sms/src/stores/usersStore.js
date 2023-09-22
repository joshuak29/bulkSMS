import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

import axios from 'axios'
import setAuthHeader from '../utils/setAuthHeader'

export const useUserStore = defineStore('user', () => {
    const users = ref([]);
    const getAllUsers = async () => {
        users.value = [];
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        const results = await axios.get('http://localhost:8080/api/users');
        results.data.forEach(user => {
            users.value.push(user);
        });
    };

    const getUser = async (id) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        const results = await axios.get(`http://localhost:8080/api/users/${id}`);
        return results.data;
    };

    const deleteUser = async (id) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        await axios.delete(`http://localhost:8080/api/users/${id}`);
        getAllUsers();
    }

    const addUser = async (data) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        await axios.post('http://localhost:8080/api/users', data);
        getAllUsers();
    }

    const updateUser = async (id, user) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
        await axios.put('http://localhost:8080/api/users', {
            id,    
            user
        });

    }

    const searchUsers = async (searchTerm) => {
        setAuthHeader("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY5NTM4MTMzNywiZXhwIjoxNjk1NDY3NzM3fQ.kjwrK_0tU6fGlsFLnS2YnWrbw0lU1sCTPurh501lZzw");
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