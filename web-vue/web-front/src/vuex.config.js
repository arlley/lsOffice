export default {
  state:{
      name:'',
      token:''
  },
  mutations:{
      initName:function (state, params) {
          state.name = params.name;
          state.token = params.token;
      }
  },
  getters:{

  },
  actions:{

  }
}
