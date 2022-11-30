import Vue from 'vue'
import cookie from "js-cookie";
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
                path: '/comments',
                name: 'comments',
                component: () => import('../pages/Comments/comments')
            }, {
                path: '/order',
                name: 'order',
                meta: {
                    requireAuth: true
                },
                component: () => import('../pages/Order/order')
            }, {
                path: '/login',
                name: 'login',
                meta: {
                    isLogin: true
                },
                component: () => import('../pages/Login/login')
            }, {
                path: '/register',
                name: 'register',
                meta: {
                    isLogin: true
                },
                component: () => import('../pages/Register/register')
            }, {
                path: '/userinfo',
                name: 'userinfo',
                meta: {
                    requireAuth: true
                },
                component: () => import('../pages/UserInfo/userinfo')
            }, {
                path: '/password',
                name: 'password',
                component: () => import('../pages/Password/password')
            }, {
                path: '/floorPlan',
                name: 'floorPlan',
                component: () => import('../pages/FloorPlan/floorPlan')
            }, {
                path: '/chat',
                name: 'chat',
                meta: {
                    requireAuth: true
                },
                component: () => import('../pages/Chat/chat')
            }, {
                path: '/map',
                name: 'map',
                component: () => import('../pages/Map/index')
            }, {
                path: '/coupon',
                name: 'coupon',
                component: () => import('../pages/Coupon/coupon')
            }, {
                path: '/404',
                name: '404',
                component: () => import('../pages/404'),
                meta: {title: '404'}
            }, {
                path: '*',
                redirect: '/404'
            }
        ]
    }
]

let router = new VueRouter({
    mode: 'history',
    routes
})

router.beforeEach((to, from, next) => {
    if (to.meta.isLogin) {
        if (!cookie.get('token')) {
            next();
        } else {
            next({
                path: '/home'
            })
        }
        return;
    }
    if (to.meta.requireAuth) {
        if (cookie.get('token')) {
            next();
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        }
    } else {
        next();
    }
})

export default router
