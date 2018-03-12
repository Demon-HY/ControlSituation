import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/views/login/login'
import Index from '@/views/index/index'

Vue.use(Router);

export default new Router({
  routes: [{
      path: '/',
      name: 'Login',
      component: Login
  }, {
    path: '/index',
    name: 'ControlSituationAdmin',
    component: Index
  }]
})
