import Vue from 'vue'
import Vuex from 'vuex'
import tagsView from './modules/tagsView'
import getters from './getters'

Vue.use(Vuex);

const store = new Vuex.Store({
  // 状态
  state : {
    author: 'Demon-HY'
  },
  modules: {
    tagsView
  },
  getters
});

export default store
