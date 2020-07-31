<template>
  <div class="bg">
    <div class="w-50 mx-auto text-center p-5">
      <h1>乾成律师事务所工作台</h1>
    </div>
    <div class="main">
      <div class="image"></div>
      <div class="w-100 bg-white p-0" style="position: relative;height: 400px;display: flex;">
        <input class="d-none" type="radio" name="widget-tab" id="login" checked="checked">
        <div id="widget-box" class="w-100 h-100">
          <div id="context-login" class="w-100 h-100">
            <input class="d-none" type="radio" name="login-select" id="account" checked="checked">
            <div id="login-box" class="w-100 h-75">
              <h2 class="text-center mt-3 mb-5">账号密码登录</h2>
              <div id="context-account" >
                <div class="w-75 mx-auto">
                  <form>
                    <div class="form-group">
                      <input
                        class="form-control"
                        type="text"
                        v-model="loginReq.userName"
                        placeholder="请输入用户名"
                      >
                    </div>
                    <div class="form-group">
                      <input
                        class="form-control"
                        type="password"
                        v-model="loginReq.password"
                        placeholder="请输入密码"
                      >
                    </div>
                  </form>
                  <div class="w-75 mx-auto">
                    <button class="btn btn-success btn-block" @click="login">登录</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import req from "../req";
import url from "../url";
export default {
  name: "v-login",
  data() {
    return {
      loginReq: {
        userName: "",
        password: ""
      },
      title: ""
    };
  },
  methods: {
    login() {
      req.POST("user/doLogin", this.loginReq).then(data => {
        if (data && data.code == 200) {
          this.$store.commit({
            type: "init",
            name: data.date
          });
          localStorage.setItem('name', data.data);
          this.$router.push("/main");
        }
      });
    }
  }
};
</script>

<style scoped>
@import url("../assets/css/font-awesome.min.css");
.bg {
  height: 100%;
  background: url("../assets/b.jpg") no-repeat center;
  background-size: cover;
}

h1 {
  color: #fff;
  font-weight: 600;
  letter-spacing: 6px;
  padding: 20px 0;
}

.main {
  margin: 0 auto;
  width: 780px;
  display: flex;
}

.image {
  width: 300px;
  height: 400px;
  background: url("../assets/m.jpg") no-repeat center;
  background-size: cover;
}
@media (max-width: 1123px) {
  .image {
    display: none;
  }
}
#login:checked ~ #widget-title #tag-login,
#register:checked ~ #widget-title #tag-register,
#forget:checked ~ #widget-title #tag-forget,
#contract:checked ~ #widget-title #tag-contract {
  background: #00ad45;
  border-right: 1px solid #000;
  color: #fff;
}
#context-login,
#login:checked ~ #widget-box > #context-login,
#register:checked ~ #widget-box > #context-register,
#forget:checked ~ #widget-box > #context-forget,
#contract:checked ~ #widget-box > #context-contract {
  display: block;
}

#context-account{
  display: none;
}
#account:checked ~ #login-title > #tag-account {
  display: none;
}
#weixin:checked ~ #login-title > #tag-weixin {
  display: none;
}
#account:checked ~ #login-box > #context-account,
#weixin:checked ~ #login-box > #context-weixin {
  display: block;
}
.login-tag > .login-tag-title {
  position: relative;
  height: 23px;
  font-size: 10px;
  line-height: 17px;
  color: #00ad45;
  max-height: 40px;
  margin: 0 10px;
  padding: 3px 4px;
  border: #00ad45 solid 1px;
  background-color: #c9f5db;
}
.login-tag > .login-tag-title > .login-tag-right {
  position: absolute;
}
.login-tag > .login-tag-title > .login-tag-right::before,
.login-tag > .login-tag-title > .login-tag-right::after {
  position: absolute;
  top: 2px;
  content: "";
  border-top: 6px transparent solid;
  border-bottom: 6px transparent solid;
  border-right: 6px transparent solid;
  border-left: 6px #c9f5db solid;
}
.login-tag > .login-tag-title > .login-tag-right::before {
  left: 5px;
  border-left: 6px #00ad45 solid;
}
.login-tag > .login-tag-title > .login-tag-right::after {
  left: 4px;
  border-left: 6px #c9f5db solid;
}
.login-tag > .login-tag-logo {
  color: #323335;
  font-size: 1.3em;
  width: 20px
}
</style>
