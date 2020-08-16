// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import "bootstrap";
import 'bootstrap/dist/js/bootstrap.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';
Vue.config.productionTip = false

/* eslint-disable no-new */
import Vuex from 'vuex'
Vue.use(Vuex)

import validation from "./validation";
Vue.prototype.$verify = validation;

import VuexConfig from './vuex.config';
const store = new Vuex.Store(VuexConfig);

import axios from "axios";
Vue.prototype.$axios = axios;

//设置axios为form-data
let baseURL = "http://121.40.152.16:8888/";
axios.defaults.headers.post['Content-Type'] = 'text/plain';
axios.defaults.headers.get['Content-Type'] = 'text/plain';
axios.defaults.withCredentials = true;
axios.defaults.baseURL = baseURL;


import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

Vue.use(Antd);
if (sessionStorage.getItem("store")) {
  store.replaceState(
    Object.assign(
      {},
      store.state,
      JSON.parse(sessionStorage.getItem("store"))
    )
  );
  sessionStorage.removeItem("store")
}
window.addEventListener("beforeunload", () => {
  sessionStorage.setItem("store", JSON.stringify(store.state));
});

// 导航狗子

router.beforeEach((to, from, next) =>{
    if(to.name == 'Login'){
      next();
    }
    let token = localStorage.getItem("token");
    if(token == null || token.length == 0){
        next({name:'Login'});
    }
    next();
});

Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
});

new Vue({
  el: '#app',
  router,
  store:store,
  components: { App },
  template: '<App/>'
})


