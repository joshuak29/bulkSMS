import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core'

import { faChartSimple, faPaperPlane, faUsers, faCalendar, faGear, faPenToSquare, faRightFromBracket, faArrowLeft } from '@fortawesome/free-solid-svg-icons'

library.add(faUsers)
library.add(faChartSimple)
library.add(faPaperPlane)
library.add(faCalendar)
library.add(faGear)
library.add(faPenToSquare)
library.add(faRightFromBracket)
library.add(faArrowLeft)

import App from '@/App.vue'
import router from '@/router'
import '@/index.css'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
