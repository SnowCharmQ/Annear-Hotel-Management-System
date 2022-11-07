import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/normalize.css';
import './assets/css/index.css';
import router from './router'

import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'
console.log(lang,'lang')
// 设置语言
locale.use(lang)


Vue.use(Element) 
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
