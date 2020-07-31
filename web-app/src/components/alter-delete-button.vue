<template>
  <span>
    <button @click="alter">修改</button>
    <button @click="del">删除</button>
  </span>
</template>
<script>
import req from "../req";
import url from "../url";
export default {
  name: "alter-delete-button",
  props: {
    local: "",
    selectIndex: ""
  },
  methods: {
    alter() {
      this.$store.commit({
        type: "select",
        name: this.local,
        index: this.selectIndex
      });
      this.$router.push(this.$utils.getModPageLink(this.local));
    },
    del() {
      this.$store.commit({
        type: "select",
        name: this.local,
        index: this.selectIndex
      });
      let id = this.$store.state[this.local].id;
      req.GET(url[this.local].delete, { id: id }).then(data => {
          this.$router.push({
            path: this.$utils.getSuccessLink(),
            query: {
              message: "删除成功",
              local: this.local
            }
          });
      });
    }
  }
};
</script>
<style scoped>
</style>
