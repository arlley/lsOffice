<template>
  <div>
    <tableNav
      localName="消息管理"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="发送消息"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">发送者</th>
          <th scope="col">消息内容</th>
          <th scope="col">发送时间</th>
          <th scope="col">是否阅读</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(message,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{message.sender}}</td>
          <td>{{message.message}}</td>
          <td>{{message.sendtime}}</td>
          <td v-if="message.isRead==1">已读</td>
          <td v-else>未读</td>
          <td>
            <button @click="del(index)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
    <vPage local="message" ></vPage>
  </div>
</template>
<script>
import req from "../../req";
import url from "../../url";
import tableNav from "../table-nav";
import vPage from "../page";
export default {
  name: "message",
  components: {
    vPage,
    tableNav
  },
  data() {
    return {
    };
  },
  methods: {
    del(index) {
      this.$store.commit(this.$utils.getSelectName(this.$options.name), index);
      let message = this.$store.state.message;
      req.POST(url.message.delete, req.myDelete(message)).then(data => {
        if (data.result == "success") {
          this.$router.push({
              path: this.$utils.getSuccessLink(),
              query: {
                message: "删除信息成功",
                local: this
              }
            });
        } else {
          this.$router.push({
              path: this.$utils.getFailureLink(),
              query: {
                message: "删除信息失败",
                local: this
              }
            });
        }
      });
    },
    
    flush() {
      this.$store.commit(this.$utils.getInitName(this));
      this.$router.push(this.$utils.getPageLink(this));
    },
    add() {
      this.$router.push(this.$utils.getAddPageLink(this));
    }
  },
  mounted() {}
};
</script>
<style scoped>
</style>
