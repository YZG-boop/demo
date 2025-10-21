import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // 引入路由

// 导入 element-plus 及样式
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// （可选）导入图标库
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import './assets/global.css' // 引入全局样式文件

const app = createApp(App)

// 注册 element-plus
app.use(ElementPlus)

// （可选）注册所有图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
// 注册路由
app.use(router)
app.mount('#app')