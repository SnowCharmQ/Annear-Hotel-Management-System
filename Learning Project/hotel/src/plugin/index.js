// 注册element-ui 插件
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// 注册 myPlugin 插件
import myplugin from './myplugin';
Vue.use(myplugin)