import axios from "axios";
import { defineStore } from "pinia";
import { useRouter } from 'vue-router';
import jwt_decode from 'jwt-decode';



import { ref, computed, nextTick } from 'vue'

import setAuthHeader from "@/utils/setAuthHeader";

export const useAuthStore = defineStore('auth', () => {
    const router = useRouter();

    const user = ref({});

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
            getUser()

            router.push({ name: 'new' })
        } catch (error) {
            console.log(error)
        }
    }

    const getUser =  async () => {
        user.value = null;

        if (isAuthenticated()) {
            const payload = jwt_decode(localStorage.getItem('token'));
            const id = parseInt(payload.id)

            setAuthHeader();
            try {
                let results = await axios.get(`http://localhost:8080/api/users/${id}`)
                user.value = results.data;
                console.log(user.value)
            } catch (error) {
                console.log(error)
            }
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
        return true;
    }

    return { login, logout, isAuthenticated, isAdmin, getUser, user };
})