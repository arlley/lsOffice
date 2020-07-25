<template>
  <div>
    <tableNav localName="首页"></tableNav>
    <h1 class="ml-5 pl-5">欢迎您,{{enrollee}}</h1>
  </div>
</template>
<script>
import tableNav from './table-nav'
export default {
  name: "v-welcome",
  components:{
    tableNav
  },
  data() {
    return {
      enrollee: this.$store.state.enrollee
    };
  },
  props: {},
  methods: {
    logout: function() {}
  },
  created() {
  }
};
</script>
<style scoped>
</style>

