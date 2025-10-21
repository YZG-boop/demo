<template>
  <div class="login-container">
    <div class="login-card">
      <h2 class="login-title">系统登录</h2>

      <!-- Element Plus 表单组件 -->
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        label-width="80px"
        class="login-form"
      >
        <!-- 用户名输入 -->
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            prefix-icon="User"
            clearable
          />
        </el-form-item>

        <!-- 密码输入 -->
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            prefix-icon="Lock"
            clearable
            show-password
          />
        </el-form-item>

        <!-- 登录按钮 -->
        <el-form-item>
          <el-button
            type="primary"
            class="login-btn"
            @click="handleLogin"
            :loading="isLoading"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus' // 消息提示
import http from '@/utils/http' // 封装的 Axios 工具

// 路由实例
const router = useRouter()

// 登录表单数据
const loginForm = reactive({
  username: 'admin', // 默认测试账号
  password: '123456' // 默认测试密码
})

// 表单验证规则
const loginRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 10, message: '用户名长度在 3-10 之间', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度至少 6 位', trigger: 'blur' }
  ]
}

// 表单引用（用于验证）
const loginFormRef = ref(null)

// 登录加载状态
const isLoading = ref(false)

// 登录逻辑
const handleLogin = async () => {
  // 先验证表单
  const valid = await loginFormRef.value.validate()
  if (!valid) return

  try {
    isLoading.value = true // 显示加载状态
    // 调用后端登录接口（根据实际地址根据实际后端调整）
    const res = await http.post('/api/auth/login', loginForm)

    //console.log('后端返回的完整响应：', res); // 新增此
    // 假设后端返回 { code: 200, data: { token }, msg: '登录成功' }
    if (res.code === 200) {
      // 存储 Token 到本地
      localStorage.setItem('token', res.data)
      ElMessage.success('登录成功！')
      // 跳转到首页
      router.push('/')
    } else {
      ElMessage.error(res.msg || '登录失败')
    }
  } catch (err) {
    console.error('登录请求失败', err)
    ElMessage.error('网络错误，请重试')
  } finally {
    isLoading.value = false // 关闭加载状态
  }
}

</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f5f7fa;
  margin: 0; /* 清除可能的默认 margin */
  padding: 0; /* 清除可能的默认 padding */
  box-sizing: border-box; /* 确保 padding 和 border 不影响总高度 */
}

.login-card {
  width: 400px;
  padding: 30px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
  color: #303133;
}

.login-form {
  margin-top: 20px;
}

.login-btn {
  width: 100%;
}
</style>