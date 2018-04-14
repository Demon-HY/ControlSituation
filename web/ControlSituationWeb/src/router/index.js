import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/views/login/login'
import Index from '@/views/index/index'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    redirect: '/login'
  }, {
      path: '/login',
      name: 'Login',
      component: Login
  }, {
    path: '/index',
    name: 'ControlSituationAdmin',
    component: Index
  }]
})
