import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from "@/utils/request";
import store from './store'
import qs from 'qs'
import axios from "axios";

Vue.config.productionTip = false
Vue.prototype.$qs = qs
Vue.use(ElementUI,{size:"mini"});
axios.defaults.withCredentials=true;

Vue.prototype.request= request;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
