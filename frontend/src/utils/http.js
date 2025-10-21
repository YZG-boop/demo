import axios from 'axios'
import { ElMessage } from 'element-plus'

// 创建 Axios 实例
const http = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '', // 接口基础地址
  timeout: 5000 // 超时时间
})

// 请求拦截器（添加 Token）
http.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}` // JWT 认证格式
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器：处理响应
http.interceptors.response.use(
  (response) => {
    const res = response.data
    if (res.code !== 200) {
      ElMessage.error(res.msg || '请求失败')
      return Promise.reject(res)
    }
    return res
  },
  (error) => {
      // 处理 401 未授权（Token 过期或无效）
      if (error.response?.status === 401) {
        localStorage.removeItem('token')
        ElMessage.error('登录已过期，请重新登录')
        window.location.href = '/login' // 跳转到登录页
      } else {
        ElMessage.error('请求失败：' + (error.message || '网络错误'))
      }
      return Promise.reject(error)
    }
)

export default http