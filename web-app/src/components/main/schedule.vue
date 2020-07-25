<template>
  <div>
        <tableNav
      localName="日程表"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="新增行程"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <th scope="col">#</th>
        <th scope="col">年份</th>
        <th scope="col">月份</th>
        <th scope="col">日期</th>
        <th scope="col">安排内容</th>
        <th scope="col">操作</th>
      </thead>
      <tbody>
        <tr v-for="(schedule,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{schedule.year}}</td>
          <td>{{schedule.month}}</td>
          <td>{{schedule.day}}</td>
          <td>{{schedule.plan}}</td>
          <td>
            <alterDeleteButton local="schedule" :selectIndex="index"></alterDeleteButton>
          </td>
        </tr>
      </tbody>
    </table>
    <vPage local="schedule" ></vPage>
  </div>
</template>
<script>
import tableNav from "../table-nav";
import alterDeleteButton from "../alter-delete-button";
import vPage from "../page";
export default {
  name: "schedule",
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
