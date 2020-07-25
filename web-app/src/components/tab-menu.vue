<template>
  <!-- 左侧菜单栏目块 -->
  <div class="leftMeun" id="leftMeun">
    <div id="logoDiv">
      <p id="logoP">
        <img id="logo" alt src="../assets/images/12.png">
        <span>乾成律师事务所</span>
      </p>
    </div>
    <div id="personInfor">
      <p id="userName">登录者：</p>
    </div>

    <div v-for="(item,index) in navList" :key="index">
      <div class="meun-title" v-if="index==0" style="padding-left:20px">系统</div>
      <div
        class="meun-item"
        :class="[item.isActive?'meun-item-active':'']"
        @click="clickNavItem(index)"
        role="tab"
        data-toggle="tab"
        style="padding-left:20px"
      >
        <router-link
          :to="item.toLink"
          style="text-decoration:none;"
          :class="[item.isActive?['text-white', 'font-weight-bold']:'']"
        >
          <span :class="item.imageClass" style="width:20px"></span>
          {{item.text}}
        </router-link>
      </div>
      <div class="meun-title" v-if="index==1" style="padding-left:20px">公共信息</div>
      <div class="meun-title" v-if="index==3" style="padding-left:20px">个人信息</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "v-tab-menu",
  data() {
    return {
      enrollee: this.$store.state.enrollee,
      navList: [
        {
          toLink: "/main",
          imageClass: "fa fa-desktop text-light text-weight-bold",
          text: "首页",
          isActive: true
        },
        {
          toLink: "/login",
          imageClass: "fa fa-remove text-light text-weight-bold",
          text: "退出系统",
          isActive: false
        },
        {
          toLink: "/main/notice",
          imageClass: "fa fa-volume-up text-light text-weight-bold",
          text: "公告管理",
          isActive: false
        },
        {
          toLink: "/main/meeting",
          imageClass: "fa fa-users text-light text-weight-bold",
          text: "会议管理",
          isActive: false
        },
        {
          toLink: "/main/address",
          imageClass: "fa fa-address-book text-light text-weight-bold",
          text: "通讯录",
          isActive: false
        },
        {
          toLink: "/main/message",
          imageClass: "fa fa-envelope text-light text-weight-bold",
          text: "消息管理",
          isActive: false
        },
        {
          toLink: "/main/worklog",
          imageClass: "fa fa-book text-light text-weight-bold",
          text: "工作日志",
          isActive: false
        },
        {
          toLink: "/main/schedule",
          imageClass: "fa fa-calendar text-light text-weight-bold",
          text: "时刻表",
          isActive: false
        }
      ]
    };
  },
  methods: {
    clear() {
      for (let item in this.navList) {
        this.$set(this.navList[item], "isActive", false);
      }
    },
    clickNavItem(index) {
      this.clear();
      this.$set(this.navList[index], "isActive", true);
    }
  }
};
</script>
<style scoped>
.leftMeun {
  position: absolute;
  box-sizing: border-box;
  width: 200px;
  height: 100%;
  background: #4d5e70 bottom;
}
@media (max-width: 1123px) {
  .leftMeun {
    display: none;
  }
}
#logoDiv {
  padding: 20px 0 20px 20px;
  height: 70px;
  background-color: #354457;
  font-size: 17px;
  color: #fff;
  vertical-align: bottom;
}
#logo {
  height: 30px;
  padding-right: 5px;
}
#logoDiv span {
  vertical-align: bottom;
}
#personInfor {
  padding: 10px 5px 10px;
  margin: 0 5px;
  color: #b3bcc5;
  border-bottom: 1px solid #354457;
  overflow-x: hidden;
  padding-left: 20px;
}
#personInfor p {
  font-size: 12px;
  margin-left: -5px;
}
#personInfor a {
  color: #b3bcc5;
  text-decoration: underline;
}
#userName {
  font-size: 15px !important;
  padding: 0;
  margin: 0;
}

.meun-title {
  color: #828e9a;
  padding-top: 10px;
  padding-bottom: 10px;
  font-size: 14px;
  font-weight: bold;
}
.meun-item {
  line-height: 40px;
  height: 40px;
  color: #aab1b7;
  cursor: pointer;
}
.meun-item a {
  color: #aab1b7;
  display: block;
}
.meun-item-active a {
  color: #c4c7cc;
  display: block;
}
.meun-item img {
  padding-right: 8px;
  height: 20px;
}
.meun-item-active {
  background-color: #3d4e60;
  border-right: 4px solid #647f9d;
  color: #fff;
}
</style>
