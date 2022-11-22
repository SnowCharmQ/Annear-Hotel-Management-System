import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Main',
        component: () => import('../pages/Main.vue'),  //按需引入组件
        children: [
            {
                path: '/',
                name: 'home',
                component: () => import('../pages/Home/home')
            }, {
                path: '/home',
                name: 'home',
                component: () => import('../pages/Home/home')
            }, {
                path: '/search',
                name: 'search',
                component: () => import('../pages/Search/search')
            }, {
                path: '/reserve',
                name: 'reserve',
                component: () => import('../pages/Reserve/reserve')
            }, {
                path: '/order',
                name: 'order',
                component: () => import('../pages/Order/order')
            }, {
                path: '/login',
                name: 'login',
                component: () => import('../pages/Login/login')
            }, {
                path: '/floorPlan',
                name: 'floorPlan',
                component: () => import('../pages/FloorPlan/floorPlan')
            }
        ]
    }
]

let router = new VueRouter({
    mode: 'history',
    routes
})

export default router
