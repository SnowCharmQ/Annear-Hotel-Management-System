import Vue from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'

import '../src/plugin'  // 导入插件
// main.js
import * as echarts from "echarts"
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

Vue.prototype.$baseUrl = 'http://localhost:12345'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
