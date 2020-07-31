/* jshint esversion: 6 */
import Vue from 'vue';
import App from './App.vue';

import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

Vue.config.productionTip = false;



// import qs from "qs";
// Vue.prototype.$qs = qs;

import axios from "axios";
Vue.prototype.$axios = axios;  //引入

//设置axios为form-data
let baseURL = "http://localhost:26001/";
axios.defaults.headers.post['Content-Type'] = 'text/plain';
axios.defaults.withCredentials = true;
axios.defaults.baseURL = baseURL;


//然后再修改原型链
Vue.prototype.$axios = axios;

//引入vue-router
import VueRouter from 'vue-router';
Vue.use(VueRouter);
//引入路由配置
import RouterConfig from './vue-router.config';
const router = new VueRouter(RouterConfig);
import Vuex from 'vuex'; //引入状态管理
Vue.use(Vuex);

Vue.use(Antd);
import VuexConfig from './vuex.config';
const store = new Vuex.Store(VuexConfig);


import utils from './utils';
Vue.prototype.$utils = utils;

import "bootstrap";
import 'bootstrap/dist/js/bootstrap.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';

var  bus = new Vue();
Vue.prototype.bus=bus;

Vue.directive('title', {
  // eslint-disable-next-line no-unused-vars
  inserted: function (el) {
    document.title = el.dataset.title
  }
});

const isLogin = function () {
  var userName = localStorage.getItem("name");
  return userName != null && userName.length > 0;
};

var vue;
router.beforeEach(function(to, from, next){
  if (to.path !== '/login' && !isLogin()) next("/login")
  else next()
})

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

vue = new Vue({
  render: h => h(App),
  router: router,
  store: store
});
vue.$mount('#app');

