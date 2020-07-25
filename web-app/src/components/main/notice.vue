<template>
  <div>
        <tableNav
      localName="公告栏"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="新增公告"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">公告撰写人</th>
          <th scope="col">公告标题</th>
          <th scope="col">公告内容</th>
          <th scope="col">公告日期</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(notice,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{notice.sender}}</td>
          <td>{{notice.title}}</td>
          <td>{{notice.content}}</td>
          <td>{{notice.sendtime}}</td>
          <td>
            <alterDeleteButton local="notice" :selectIndex="index"></alterDeleteButton>
          </td>
        </tr>
      </tbody>
    </table>
    <vPage local="notice" ></vPage>
  </div>
</template>
<script>
import tableNav from "../table-nav";
import alterDeleteButton from "../alter-delete-button";
import vPage from "../page";
export default {
  name: "notice",
  components: {
    vPage,
    tableNav,
    alterDeleteButton
  },
  data() {
    return {
      list: []
    };
  },
  methods: {
    
    flush() {
      this.$store.commit(this.$utils.getInitName(this));
      this.$router.push(this.$utils.getPageLink(this));
    },
    add() {
      this.$router.push(this.$utils.getAddPageLink(this));
    }
  }
};
</script>
<style scoped>
</style>
