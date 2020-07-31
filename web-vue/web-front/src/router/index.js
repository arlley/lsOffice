import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/login/Login'
import Main from '@/views/main/Main'
import Welcome from "../views/main/Welcome";
import User from "../views/main/User";
import AddUser from "../views/main/AddUser";
import Success from "../views/main/Success";
import AlertUser from "../views/main/AlertUser";
import Pay from '../views/main/Pay'
import AddPay from "../views/main/AddPay";
import AlertPay from "../views/main/AlertPay";
import AddCase from "../views/main/AddCase";

Vue.use(Router);
Promise

export default new Router({
    routes: [

        {
            path: '/main',
            name: 'Main',
            component: Main,
            children:[{
                path:'/welcome',
                name:'Welcome',
                component: Welcome
            },{
              path:'/user',
              name:'User',
              component: User
            },{
              path:'/addUser',
              name:'AddUser',
              component: AddUser
            },{
              path:'/success',
              name:'Success',
              component: Success
            },{
              path:'/alertUser',
              name:'AlertUser',
              component: AlertUser
            },{
              path:'/pay',
              name:'Pay',
              component: Pay
            },{
              path:'/addPay',
              name:'AddPay',
              component: AddPay
            },{
              path:'/alertPay',
              name:'AlertPay',
              component: AlertPay
            },{
              path:'/addCase',
              name:'AddCase',
              component: AddCase
            }]
        },{
          path: '/*',
          name: 'Login',
          component: Login
        },
    ]
})
