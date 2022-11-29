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


import VueAMap from 'vue-amap';

Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: 'f3b4637de1fe910d5be8399da35b8ce7',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor'],
  // 默认高德 sdk 版本为 1.4.4
  v: '1.4.4'
})

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
