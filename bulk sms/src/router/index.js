import { createRouter, createWebHistory } from 'vue-router'
import NewSMS from '@/components/main/NewSMS.vue'
import Users from '@/components/main/Users.vue'
import Dashboard from '@/components/main/dashboard/Dashboard.vue'
import Login from '@/components/Login.vue'

import { useAuthStore } from '@/stores/authStore'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/new',
      name: 'new',
      component: NewSMS,
      meta: {
        auth: true
      }
    },
    {
      path: '/users',
      name: 'users',
      component: Users,
      meta: {
        auth: true,
        admin: true
      }
    },
    {
      path: '/Dashboard',
      name: 'history',
      component: Dashboard,
      meta: {
        auth: true
      }
    },
    {
      path: '/',
      name: 'home',
      redirect: { name: 'login' }
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        auth: false
      }
    }
  ]

})

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();

  if (!to.meta.auth && authStore.isAuthenticated()) {
    console.log("failed not authenticated")
    next({ name: "new" });
  } else if (to.meta.auth && to.meta.admin && (!authStore.isAuthenticated() || !authStore.isAdmin())) {
    next(from.fullPath);
  } else if (to.meta.auth && !authStore.isAuthenticated()) {
    next({ name: "login" });
  }
  else {
    next();
  }
})
export default router
