import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path' // 需引入 path 模块

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 后端接口地址
        changeOrigin: true // 开启跨域伪装（关键，必须设为 true）
      }
    }
  },
  resolve: {
      alias: {
        // 配置 @ 指向 src 目录
        '@': path.resolve(__dirname, './src')
      }
    },
  // 打包配置（输出到后端的static目录，方便部署）
    build: {
      outDir: '../demo/src/main/resources/static', // 打包路径指向后端静态资源目录
      emptyOutDir: true // 清空目标目录
    }
})