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

Vue.use(Vuex);

Vue.config.productionTip = true;

// 路由请求拦截
router.beforeEach((to, from, next) => {
  //NProgress.start();
  // if (to.path === '/toLogin') {
  //   sessionStorage.removeItem('user');
  // }
  // let user = JSON.parse(sessionStorage.getItem('user'));
  // if (!user && to.path !== '/login') {
  //   next({ path: '/login' })
  // } else {
  //   next()
  // }

  next()
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
