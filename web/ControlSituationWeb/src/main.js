// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'

import 'element-ui/lib/theme-chalk/index.css'

import store from './store/store'
import '../static/css/main.css'
import '@/utils/element-init'
// 引入图标库
import './assets/icon/iconfont.css'

Vue.use(Vuex);

Vue.config.productionTip = true;

import {checkLogin} from '@/api/user/auth_api'

// 路由请求拦截
router.beforeEach((to, from, next) => {
  if (to.path === '/' || to.path === '/login') {
    // 检查本地的 sessionStorage 是否存在token
    if (!sessionStorage.getItem('token')) {
      next();
    } else {
      // 调用验证登录接口检查token是否有效
      checkLogin().then(result => {
        if (result.code === '3000002' || result.code === '3000001') {
          sessionStorage.removeItem('token');
          next();
        } else {
          next({path: '/index'});
        }
      });
    }
  } else {
    // 检查本地的 sessionStorage 是否存在token
    if (!sessionStorage.getItem('token')) {
      next({path: '/login'});
    }
  }

  next()
});

// 定义全局过滤器
Vue.filter('toString', function (value) {
  if (!value) return '';
  return value.toString();
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
