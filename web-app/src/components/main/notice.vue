<template>
  <div>
        <tableNav
      localName="案件管理"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="新增案件"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">收案日期</th>
          <th scope="col">委托人</th>
          <th scope="col">对方当事人</th>
          <th scope="col">承办律师</th>
          <th scope="col">结案</th>
          <th scope="col">代理费(元)</th>
          <th scope="col">已付款(元)</th>
          <th scope="col">已开票(元)</th>
          <th scope="col">收案审批</th>
          <th scope="col">收案审批人</th>
          <th scope="col">合同</th>
          <th scope="col">档案</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(notice,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{notice.time}}</td>
          <td>{{notice.bailor}}</td>
          <td>{{notice.party}}</td>
          <td>{{notice.uid}}</td>
          <td><code-show type="caseState" v-bind:code="notice.state"></code-show></td>
          <td>{{notice.proxyAmount}}</td>
          <td>{{notice.alreadyAmount}}</td>
          <td>{{notice.alreadyPayInvoiceAmount}}</td>
          <td><code-show type="approveState" v-bind:code="notice.approveState"></code-show></td>
          <td>{{notice.approveUid}}</td>
          <td>{{notice.contractNo}}</td>
          <td>{{notice.recordNo}}</td>
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
import codeShow from "../code-show"
export default {
  name: "notice",
  components: {
    vPage,
    tableNav,
    alterDeleteButton,
    codeShow
  },
  created(){
    this.$store.commit(this.$utils.getInitName(this));
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
