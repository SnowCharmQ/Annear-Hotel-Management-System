 
// 请求相关的方法

import axios from "axios"

// 加载进度条
import NProgress from "nprogress"
import 'nprogress/nprogress.css'

// 在程序加载前就会有，所以不能通过这个传输 Token
var instance = axios.create({
    // 接口地址根路径
    baseURL: 'http://bingjs.com:83',
    // 超时时间
    timeout: 300000
})

// 创建一个 get 请求方法 
let get = async function(url, params){
    let {data} = await instance.get(url, {params});
    return data;
}

// 创建一个post方法
let post = async function(url, params){
    let {data} = await instance.post(url, params);
    return data;
}


// 创建一个将 token 信息保存到请求头的方法
let setToken = function(){
    instance.defaults.headers.common['token'] = sessionStorage.getItem('token')
}


// 请求的拦截器(请求前触发)
instance.interceptors.response.use(
    function (config){
        NProgress.start();
        return config;
}, 
    function (error) {
        NProgress.done();
        return Promise.reject(error)
});

// 回复拦截器（请求结束触发）
instance.interceptors.response.use(function (response){
    NProgress.done();
    return response;
}, function (error){
    NProgress.done();
    return Promise.reject(error)
})

// 导出 get 和 post 方法
export{
    get, post, setToken
}