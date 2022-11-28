import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import("../views/Login.vue")

  },
  {
    path: '/layout',
    component: () => import("@/views/Layout.vue"),
    children: [
      {
        path: 'customer',
        component: () => import('@/views/Role/Customer.vue')
      },
      {
        path: 'admin',
        component: () => import('@/views/Role/Admin.vue')
      },
      {
        path: 'type',
        component:() => import('@/views/Room/Type.vue')
      },
      {
        path: 'room',
        component:() => import('@/views/Room/Room.vue')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
