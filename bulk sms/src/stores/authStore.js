import axios from "axios";
import { defineStore } from "pinia";
import { useRouter } from 'vue-router';



import { ref, computed } from 'vue'

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

            router.push({ name: 'new' })
        } catch (error) {
            console.log(error)
        }
    }

    return { login };
})