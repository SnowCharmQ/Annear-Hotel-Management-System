import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
	{
		path:'/',
		name:'Main',
		component:() => import('../pages/Main.vue'),  //按需引入组件
		children:[
			{
				path:'/',
				name:'home',
				component:() => import('../pages/Home')
			},{
				path:'/Search',
				name:'Search',
				component:() => import('../pages/Search')
			},{
				path:'/Reserve',
				name:'Reserve',
				component:() => import('../pages/Reserve')
			} ,{
				path:'/Login',
				name:'Login',
				component:() => import('../pages/Login')
			}
		]
	}
]

let router  = new VueRouter({
	mode:'history',
	routes
})

export default router