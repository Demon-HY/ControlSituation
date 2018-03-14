// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store/store'

import '../static/css/main.css'
import loginApi from './api/user/login_api'




Vue.use(ElementUI);
Vue.use(Vuex);

Vue.config.productionTip = false;
Vue.prototype.$loginApi = loginApi;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
