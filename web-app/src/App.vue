<template>
  <div id="app" v-title data-title="乾成律师事务所工作台">
    <router-view></router-view>
  </div>
</template>

<script>
// import login from './components/login';

export default {
  name: 'app',
  components: {
    // login
  }
};
</script>

<style>
html,body{
  height: 100%;
}
#app{
  margin: 0px;
  padding: 0px;
  height: 100%;
  width: 100%;
}
</style>
