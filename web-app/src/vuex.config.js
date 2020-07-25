/* jshint esversion: 6*/
import req from "./req";
import url from "./url";
import utils from "./utils";
export default {
    state: {
        user: {
            userName: ''
        }
    },
    mutations: {
        init(state, param) {
            state.user.userName = param.userName;
        }
    }, getters: {
        isLogin: state =>{
            return state.user.userName != null && state.user.userName.length > 0;
        }
    }, actions: {

    }
};