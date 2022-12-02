// 开发一个插件
import {get, post, setToken} from '../utils/request'

export default{
    // 插件中必须包含一个 install 方法
    install:function(Vue){
        // 给Vue 混入成员
        Vue.mixin({
            methods: {
                $get(url, params){
                    return get(url, params)
                },
                $post(url, params){
                    return post(url, params)
                },
                // 执行该方法就会将浏览器缓存里面的token信息保存到ajax的请求当中
                $setToken(){
                    setToken()
                }
            },
        })
    }
}