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
import Custom from "../views/main/Custom"
import AddCustom from "../views/main/AddCustom";
import AlertCustom from "../views/main/AlertCustom";
import Case from "../views/main/Case";
import AddLawer from "../views/main/AddLawer";
import Lawyer from "../views/main/Lawyer";
import PayDetailAll from "../views/main/PayDetailAll";
import AddLawyerPay from "../views/main/AddLawyerPay";
import AddLawyerOut from "../views/main/AddLawyerOut";
import CaseDetail from "../views/main/CaseDetail";
import PayDetailMy from "../views/main/PayDetailMy";
import AddLawyerMyPayIn from "../views/main/AddLawyerMyPayIn";
import AddLawyerMyPayOut from "../views/main/AddLawyerMyPayOut";

Vue.use(Router);


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
            },{
              path:'/custom',
              name:'Custom',
              component: Custom
            },{
              path:'/addCustom',
              name:'AddCustom',
              component: AddCustom
            },{
              path:'/alertCustom',
              name:'AlertCustom',
              component: AlertCustom
            },{
              path:'/case',
              name:'Case',
              component: Case
            },{
              path:'/addLawer',
              name:'AddLawer',
              component: AddLawer
            },{
              path:'/lawyer',
              name:'Lawyer',
              component: Lawyer
            },{
              path:'/payDetail',
              name:'PayDetail',
              component: PayDetailAll
            },{
              path:'/addLawyerPay',
              name:'AddLawyerPay',
              component: AddLawyerPay
            },{
              path:'/addLawyerOut',
              name:'AddLawyerOut',
              component: AddLawyerOut
            },{
              path:'/caseDetail',
              name:'CaseDetail',
              component: CaseDetail
            },{
              path:'/payDetailMy',
              name:'PayDetailMy',
              component: PayDetailMy
            },{
              path:'/addLawyerMyPayIn',
              name:'AddLawyerMyPayIn',
              component: AddLawyerMyPayIn
            },{
              path:'/addLawyerMyPayOut',
              name:'AddLawyerMyPayOut',
              component: AddLawyerMyPayOut
            }]
        },{
          path: '/*',
          name: 'Login',
          component: Login
        },
    ]
})
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
