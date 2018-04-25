import Vue from 'vue'
import Router from 'vue-router'

import Index from '../views/index/index'

const _import = require('./_import_' + process.env.NODE_ENV);

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    redirect: '/login'
  }, {
      path: '/login',
      name: 'index',
      component: _import('login/login')
  }, {
    path: '/index',
    name: 'index',
    component: Index,
    children: [{
      path: 'user',
      name: 'user',
      component: _import('index/modules/system/User')
    }]
  }, {
    path: '/system',
    name: 'system',
    component: Index,
    children: [{
      path: 'user',
      component: _import('index/modules/system/User')
    }]
  }]
})
