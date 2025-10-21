<template>
  <div>
    <h2>首页</h2>
    <p>登录成功！当前用户：{{ username }}</p>
    <button @click="logout">退出登录</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const username = ref('admin') // 实际应从接口获取用户信息

const logout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>