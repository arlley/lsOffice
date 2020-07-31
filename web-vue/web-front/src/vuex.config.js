export default {
  state:{
      name:'',
      token:''
  },
  mutations:{
      initName:function (state, params) {
          debugger;
          state.name = params.name;
          state.token = params.token;
      }
  },
  getters:{

  },
  actions:{

  }
}
