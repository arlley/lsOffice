/* jshint esversion: 6*/
import req from "./req";
import url from "./url";
import utils from "./utils";
export default {
    state: {
        user: {
            name: ''
        },
        users:[{
            id:'',
            name:'',
            tel:'',
            state:''
        }],
        addressList:[],
        pageArray:[{}],
    },
    mutations: {
        init(state, param) {
            state.user.name = param.name;
            this.commit("initAddress");
        },
        initAddress(state){
            // 访问后端
            req.POST("user/address").then(function (response) {
                debugger;
                state.addressList = response.date;
            });
        },
        setPageArray(state, array) {
            state.pageArray=array;
        },
        select(state, param) {
            state[param.name] = {};
            state[param.name] = state[utils.getLocalArrayName(param.name)][param.index];
            state.index = param.index;
        }
    }, getters: {
        isLogin: state =>{
            return state.user.userName != null && state.user.userName.length > 0;
        }
    }, actions: {

    }
};