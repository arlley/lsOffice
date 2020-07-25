<template>
  <div>
    <tableNav
      localName="通讯录"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="新增联系地址"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">姓名</th>
          <th scope="col">性别</th>
          <th scope="col">手机</th>
          <th scope="col">Email</th>
          <th scope="col">QQ号码</th>
          <th scope="col">工作单位</th>
          <th scope="col">地址</th>
          <th scope="col">邮编</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(address,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{address.name}}</td>
          <td>{{address.sex}}</td>
          <td>{{address.mobile}}</td>
          <td>{{address.email}}</td>
          <td>{{address.qq}}</td>
          <td>{{address.company}}</td>
          <td>{{address.address}}</td>
          <td>{{address.postcode}}</td>
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
