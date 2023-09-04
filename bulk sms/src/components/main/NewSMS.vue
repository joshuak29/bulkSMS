<template>
    <!-- body below header -->
    <div class="w-full max-h-full">
        <div class="w-full flex flex-row p-6 gap-6">
            <div class="flex flex-col w-3/5 gap-5">
                <input type="text" placeholder="Sender ID" class="input">
                <textarea placeholder='Text Message...' cols="30" rows="15" class="text"></textarea>
                <button>Schedule Send Time</button>
            </div>
            <div class="flex flex-col w-2/5 gap-5 pt-16">
                <textarea placeholder="Numbers" cols="30" rows="15" class="text"></textarea>
                <div class="text-right">
                    <button class="mr-6">SAVE</button>
                    <button>CANCEL</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
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

button:hover {
    background-color: #F2F2F2;
    box-shadow: 0px 0px 20px -18px;
}

button:active {
    transform: scale(0.95);
}</style>