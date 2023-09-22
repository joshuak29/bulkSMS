<template>
    <div class="z-10 w-full h-full fixed left-0 top-0 bg-white flex items-center justify-center">
        <div class="login-card">
            <div class="card-header">
                <div class="log font-semibold">Login</div>
            </div>
            <form @submit.prevent="login">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input required v-model="username" name="username" id="username" type="text">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input required v-model="password" name="password" id="password" type="password">
                </div>
                <div class="form-group">
                    <input value="Login" type="submit"
                        class="w-full bg-blue-300 text-white py-3 px-5 my-2 mx-0 border-none rounded-s cursor-pointer hover:text-black hover:bg-blue-200">
                </div>
            </form>
        </div>
    </div>
</template>
<script setup>
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/authStore';
import { ref } from 'vue';

const router = useRouter();
const authStore = useAuthStore();

const username = ref();
const password = ref();

const login = () => {
    authStore.login(username.value, password.value);
}
</script>
<style scoped>
.login-card {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #e8e8e8;
    box-shadow: 2px 2px 10px #ccc;
}

.card-header {
    text-align: center;
    margin-bottom: 20px
}

.card-header .log {
    margin: 0;
    font-size: 24px;
    color: black;
}

.form-group {
    margin-bottom: 15px;
}

label {
    font-size: 18px;
    margin-bottom: 5px;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 12px 20px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    transition: 0.5s;
}
</style>