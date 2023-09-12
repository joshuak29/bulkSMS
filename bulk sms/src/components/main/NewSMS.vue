<template>
    <!-- body below header -->
    <div class="w-full max-h-full">
        <form class="w-full flex flex-col p-6 gap-6" @submit.prevent="createNewCampaign">
            <!-- senderId and numbers file -->
            <div class="flex flex-row w-full justify-between pr-6">
                <input type="text" placeholder="Sender ID" required class="input" v-model="senderId">

                <label for="file" class="flex justify-center items-center bg-gray-300 w-64 rounded-xl font-semibold">
                    Select Numbers file (.csv)
                </label>
                <input type="file" required accept=".csv" @change="getNumbers" ref="numbers" name="numbers-file" id="file" class="hidden">
            </div>

            <!-- text message area -->
            <div class="flex flex-row">
                <textarea placeholder='Text Message...' required class="text w-full" rows="12"
                    v-model="textMessage"></textarea>
            </div>

            <!-- schedule, create and cancel buttons -->
            <div class="flex flex-row justify-between">
                <label class="font-semibold">
                    Schedule Time:
                    <input placeholder="Schedule time" type="datetime-local" required v-model="scheduleDatetime"
                        class="ml-4 px-3 rounded-md border border-gray-300 font-normal">
                </label>

                <div class="text-right">
                    <input type="submit" class="mr-6" value="Create">
                    <button>CANCEL</button>
                </div>
            </div>
        </form>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'

import { useCampaignStore } from '@/stores/CampaignsStore';

const campaignStore = useCampaignStore();
const router = useRouter();

const numbers = ref(null);
const parsedNumbers = ref();

const getNumbers = () => {
    if (numbers.value == null) { }
    else if (numbers.value.files[0].type != 'text/csv') { }
    else {
        const numbersCsv = numbers.value.files[0];
        const reader = new FileReader();

        reader.onload = function (e) {
            const text = e.target.result;
            parsedNumbers.value = text.split('\n');
            // console.log(typeof (JSON.stringify(parsedNumbers.value)));
        }

        reader.readAsText(numbersCsv)
    }
}

const createNewCampaign = () => {
    getNumbers();
    campaignStore.addCampaign({
        senderId: senderId.value,
        textMessage: textMessage.value,
        schedule: scheduleDatetime.value,
        numbers: JSON.stringify(parsedNumbers.value)
    })

    router.push({ name: 'history' })
}

const senderId = ref();
const textMessage = ref();
const scheduleDatetime = ref();
</script>
<style scoped>
.input {
    width: 100%;
    max-width: 220px;
    height: 45px;
    padding: 12px;
    border-radius: 12px;
    border: 1.5px solid lightgrey;
    outline: none;
    transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
    box-shadow: 0px 0px 20px -18px;
}

.input:hover,
.text:hover {
    border: 2px solid lightgrey;
    box-shadow: 0px 0px 20px -17px;
}

.input:active,
.text:active {
    transform: scale(0.95);
}

.input:focus,
.text:focus {
    border: 2px solid grey;
}

.text {
    padding: 12px;
    transition: all 0.3s cubic-bezier(0.19, 1, 0.22, 1);
    border-radius: 12px;
    border: 1.5px solid lightgrey;
    outline: none;
    box-shadow: 0px 0px 20px -18px;
}

input[type="submit"],
button {
    width: fit-content;
    min-width: 100px;
    height: 45px;
    padding: 8px;
    border-radius: 5px;
    border: 2.5px solid #E0E1E4;
    box-shadow: 0px 0px 20px -20px;
    cursor: pointer;
    background-color: white;
    transition: all 0.2s ease-in-out 0ms;
    user-select: none;
    font-family: 'Poppins', sans-serif;
}

input[type="submit"],
button:hover {
    background-color: #F2F2F2;
    box-shadow: 0px 0px 20px -18px;
}

input[type="submit"],
button:active {
    transform: scale(0.95);
}
</style>