import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'// 支持
import 'element-ui/lib/theme-chalk/index.css' // 样式
import api from './http/index' // 引入所有插件 包括妆后的axios

Vue.config.productionTip = false
// 使用ELementUI
Vue.use(ElementUI)
// 使用自己封装的api
Vue.use(api)
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
