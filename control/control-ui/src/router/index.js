// 路由
import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import Home from '../views/Home'
import NotFound from '../views/404'
import Cookies from 'js-cookie'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/users/login',
      name: 'login',
      component: Login
    },
    {
      path: '/404',
      name: 'notFound',
      component: NotFound
    }
  ]
})


router.beforeEach((to,from,next) => {
 //等录后 会把用户信息保存会话
  // 存在时间为会话生命周期， 页面关闭即失效
  let user =sessionStorage.getItem('user')
  //如果是登录
  if(to.path == '/users/login'){
    if(user){
      //会话里存在用户信息 代表已经登录 跳到首页
      next({path: '/'})
    }else {
      //放行
      next()
    }
  }else {
    //不是登录 用户不存在
    if(!user){
      next({path: 'users/login'})
    }else {
      //存在 放行
      next()
    }
  }
})
export default router
