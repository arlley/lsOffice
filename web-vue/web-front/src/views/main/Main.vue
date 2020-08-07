<template>
  <div>
    <div>
      <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
          <p id="logoP">
            <img id="logo" alt src="../../assets/images/12.png">
            <span>乾成律师事务所</span>
          </p>
        </div>
        <div>
          <a-menu theme = dark mode="inline" :open-keys="openKeys" @openChange="onOpenChange" style="background:transparent">
            <a-menu-item key="Welcome" @click="menuClick">
              <a-icon type="home" /><span>首页</span>
            </a-menu-item>
            <a-sub-menu key="sub2" >
              <span slot="title" ><a-icon type="user" /><span>员工信息</span></span>
              <a-menu-item key="User" @click="menuClick">
                员工信息
              </a-menu-item>
              <a-menu-item key="AddUser" @click="menuClick">
                添加员工
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub3" >
              <span slot="title" ><a-icon type="dollar" /><span>收支信息</span></span>
              <a-menu-item key="Pay" @click="menuClick">
                收支信息
              </a-menu-item>
              <a-menu-item key="AddPay" @click="menuClick">
                添加收支
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub5" >
              <span slot="title" ><a-icon type="usergroup-add" /><span>客户</span></span>
              <a-menu-item key="Custom" @click="menuClick">
                我的客户
              </a-menu-item>
              <a-menu-item key="AddPay" @click="menuClick">
                对方当事人
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub4" >
              <span slot="title" ><a-icon type="container" /><span>案件信息</span></span>
              <a-menu-item key="Case" @click="menuClick">
                案件信息
              </a-menu-item>
              <a-menu-item key="AddCase" @click="menuClick">
                添加案件
              </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub6" >
              <span slot="title" ><a-icon type="container" /><span>人事</span></span>
              <a-menu-item key="Lawyer" @click="menuClick">
                员工信息
              </a-menu-item>
              <a-menu-item key="AddLawer" @click="menuClick">
                添加员工
              </a-menu-item>
            </a-sub-menu>
          </a-menu>
        </div>
      </div>
      <!-- 右侧具体内容栏目 -->
      <div id="rightContent">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
    export default {
        data(){
            return {
                rootSubmenuKeys: ['Welcome', 'sub2', 'sub3', 'sub4','sub5', 'sub6'],
                openKeys: ['Welcome'],
            };
        },
        methods:{
            onOpenChange(openKeys) {
                const latestOpenKey = openKeys.find(key => this.openKeys.indexOf(key) === -1);
                if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
                    this.openKeys = openKeys;
                } else {
                    this.openKeys = latestOpenKey ? [latestOpenKey] : [];
                }
            },
            menuClick:function (opt) {
                this.$router.push({name: opt.key}, null);
            }
        }
    };
</script>

<style scoped>
  #rightContent {
    /*position: absolute;*/
    box-sizing: border-box;
    float: left;
    box-sizing: border-box;
    padding-left: 200px;
    overflow-y: overlay;
    overflow-x: hidden;
    /*background-color: #eff3f6;*/
    clear: both;
    color: #717592;
    min-width: 100%;
    min-height: 100%;
  }
  @media (max-width: 1123px) {
    #rightContent {
      padding-left: 0;
    }
  }

  .leftMeun {
    position: fixed;
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
