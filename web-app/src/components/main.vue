<template>
  <div>
    <div></div>
    <div>
      <v-tab-menu></v-tab-menu>
      <!-- 右侧具体内容栏目 -->
      <div id="rightContent">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import vTabMenu from "./tab-menu.vue";
export default {
  name: "v-main",
  components: {
    vTabMenu
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
</style>
