<template>
  <div>
    <tableNav localName="Failure" showFirstBtn="true" firstBtnName="返回" :firstCallBack="clinkBack"></tableNav>
    <div
      class="mx-auto d-flex justify-content-center align-items-center"
      style="height:800px;width:400px"
    >
      <div class="w-75 h-25 d-flex justify-content-center" style="width:300;">
        <span class="fa fa-remove text-danger" style="font-size:100px;"></span>
        <span style="font-size:20px;line-height:100px;width:200px;">{{message}}</span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "failure",
  data() {
    return {
      message: this.$route.query.message,
      local: ""
    };
  },
  methods: {
    clinkBack() {
      this.backLink = this.$route.query.backLink;
      this.$store.commit(this.$utils.getInitName(this.local));

      this.$router.push(this.backLink);
    }
  }
};
</script>
<style scoped>
</style>
