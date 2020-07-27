<template>
  <div>
    <tableNav
      localName="人事管理"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="新增人员"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">姓名</th>
          <th scope="col">手机</th>
          <th scope="col">状态</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(address,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{address.name}}</td>
          <td>{{address.tel}}</td>
          <td v-if="address.state == 1">在职</td>
          <td v-if="address.state == 0">离职</td>
          <td>
            <alterDeleteButton local="address" :selectIndex="index"></alterDeleteButton>
          </td>
        </tr>
      </tbody>
    </table>
    <vPage local="address" ref="page"></vPage>
  </div>
</template>
<script>
import tableNav from "../table-nav";
import alterDeleteButton from "../alter-delete-button";
import vPage from "../page";
export default {
  name: "v-address",
  components: {
    vPage,
    tableNav,
    alterDeleteButton
  },
  data() {
    return {
    };
  },
  amounted(){

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
