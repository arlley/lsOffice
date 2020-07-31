<template>
  <div>
      <tableNav
      localName="事务所收支管理"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="添加收支"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">收支类别</th>
          <th scope="col">发生日期</th>
          <th scope="col">收支金额</th>
          <th scope="col">支付方式</th>
          <th scope="col">案号</th>
          <th scope="col">录入日期</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(meeting,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td><code-show type="income" :code="meeting.type"></code-show> </td>
          <td>{{meeting.time}}</td>
          <td>{{meeting.amount}}</td>
          <td><code-show type="payType" :code="meeting.payType"></code-show> </td>
          <td>{{meeting.caseNo}}</td>
          <td>{{meeting.createTime}}</td>
          <td>
            <alterDeleteButton local="meeting" :selectIndex="index"></alterDeleteButton>
          </td>
        </tr>
      </tbody>
    </table>
    <vPage local="meeting" ></vPage>
  </div>
</template>
<script>
import tableNav from "../table-nav";
import alterDeleteButton from "../alter-delete-button";
import vPage from "../page";
import payTypeSelectCode from "../pay-type-select";
import codeShow from "../code-show"
import selectCode from "../pay-type-select"
export default {
  name: "meeting",
  components: {
    vPage,
    tableNav,
    alterDeleteButton,
    payTypeSelectCode,
    codeShow,
    selectCode
  },
  data() {
    return {
      
    };
  },
  created(){
    this.flush();
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
}
</script>
<style scoped>
</style>
