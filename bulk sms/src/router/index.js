import { createRouter, createWebHistory } from 'vue-router'
import NewSMS from '@/components/main/NewSMS.vue'
import Users from '@/components/main/Users.vue'
import Dashboard from '@/components/main/dashboard/Dashboard.vue'
import Login from '@/components/Login.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/new',
      name: 'new',
      component: NewSMS
    },
    {
      path: '/users',
      name: 'users',
      component: Users
    },
    {
      path: '/Dashboard',
      name: 'history',
      component: Dashboard
    },
    {
      path: '/',
      name: 'home',
      redirect: {name: 'login'}
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

export default router
