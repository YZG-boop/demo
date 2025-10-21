import { createRouter, createWebHistory } from 'vue-router'

// 导入组件（先创建空组件，后续完善）
const Login = () => import('@/views/Login.vue')
const Home = () => import('@/views/Home.vue')

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { requiresAuth: false } // 无需登录即可访问
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: true }  // 需要登录才能访问
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/login' // 未匹配路由重定向到登录页
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫：验证登录状态
router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('token') // 假设用localStorage存Token
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login') // 需要登录但未登录，跳转到登录页
  } else {
    next()
  }
})

export default router