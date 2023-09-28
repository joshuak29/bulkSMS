import axios from "axios";
import { defineStore } from "pinia";
import { useRouter } from 'vue-router';



import { ref, computed, nextTick } from 'vue'

import setAuthHeader from "@/utils/setAuthHeader";

export const useAuthStore = defineStore('auth', () => {
    const router = useRouter();

    const login = async (username, password) => {
        setAuthHeader();

        try {
            let results = await axios.post('http://localhost:8080/api/auth/login', {
                username,
                password
            })

            console.log(results)

            localStorage.setItem('token', results.data.token);

            await nextTick()
            router.push({ name: 'new' })

        } catch (error) {
            console.log(error)
        }
    }
    const logout = () => {
        localStorage.removeItem('token');
        router.push({name: 'login'});
    }

    const isAuthenticated = () => {
        const token = localStorage.getItem('token');
        return token != null;
    }

    const isAdmin = () => {
        return false;
    }

    return { login, logout, isAuthenticated, isAdmin };
})