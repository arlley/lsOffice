/* jshint esversion: 6*/
import req from "./req";
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
        meetingList:[],
        noticeList:[],
        pageArray:[{}],
    },
    mutations: {
        init(state, param) {
            state.user.name = param.name;
            this.commit("initAddress");
            this.commit("initMeeting");
            this.commit("initNotice");
        },
        initAddress(state){
            // 访问后端
            req.POST("user/address").then(function (response) {
                state.addressList = response.data;
            });
        },
        initMeeting(state){
            // 访问后端
            req.GET("pay/pays").then(function (response) {
                console.log(response);
                state.meetingList = response.data;
            });
        },
        initNotice(state){
            req.GET("case/list").then(function (response) {
                state.noticeList = response.data;
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