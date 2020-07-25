<template>
  <div>
        <tableNav
      localName="工作日志"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="新增工作日志"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <th scope="col">#</th>
        <th scope="col">年份</th>
        <th scope="col">月份</th>
        <th scope="col">日期</th>
        <th scope="col">日志标题</th>
        <th scope="col">日志内容</th>
        <th scope="col">记录时间</th>
        <th scope="col">操作</th>
      </thead>
      <tbody>
        <tr v-for="(worklog,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{worklog.year}}</td>
          <td>{{worklog.month}}</td>
          <td>{{worklog.day}}</td>
          <td>{{worklog.title}}</td>
          <td>{{worklog.description}}</td>
          <td>{{worklog.logtime}}</td>
          <td>
            <alterDeleteButton local="worklog" :selectIndex="index"></alterDeleteButton>
          </td>
        </tr>
      </tbody>
    </table>
    <vPage local="worklog" ></vPage>
  </div>
</template>
<script>
import alterDeleteButton from "../alter-delete-button";
import tableNav from "../table-nav";
import vPage from "../page";
export default {
  name: "worklog",
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
