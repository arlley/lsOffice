<template>
  <div>
      <tableNav
      localName="会议"
      showFirstBtn="true"
      firstBtnName="刷新"
      :firstCallBack="flush"
      showSecondBtn="true"
      secondBtnName="安排会议"
      :secondCallBack="add"
    ></tableNav>
    <table class="table table-bordered table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">会议主持人</th>
          <th scope="col">会议开启时间</th>
          <th scope="col">会议结束时间</th>
          <th scope="col">会议地点</th>
          <th scope="col">会议标题</th>
          <th scope="col">会议内容</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(meeting,index) in this.$store.state.pageArray" :key="index">
          <th scope="row">{{index+1}}</th>
          <td>{{meeting.sender}}</td>
          <td>{{meeting.starttime}}</td>
          <td>{{meeting.endtime}}</td>
          <td>{{meeting.address}}</td>
          <td>{{meeting.title}}</td>
          <td>{{meeting.content}}</td>
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
export default {
  name: "meeting",
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
}
</script>
<style scoped>
</style>
