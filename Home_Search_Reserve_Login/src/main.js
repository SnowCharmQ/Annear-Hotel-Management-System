import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/normalize.css';
import './assets/css/index.css';
import '../static/config/index.js'
import router from './router'
import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'
import axios from "axios";
import request from "./utils/request";
import * as echarts from "echarts"
Vue.prototype.$echarts = echarts
// 设置语言
locale.use(lang)


Vue.use(Element)
Vue.config.productionTip = false
axios.defaults.withCredentials = true
Vue.prototype.$http = request // ajax请求方法
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
