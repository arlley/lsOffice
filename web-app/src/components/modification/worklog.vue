<template>
  <div class="w-100">
    <tableNav localName="修改工作日志" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <form class="m-75 mx-auto">
        <div class="pt-sm-3" v-for="(value,key,index) in worklog" :key="index">
          <div class="d-none" v-if="key=='id'"></div>
          <div class="form-group row" v-else-if="key=='title'||key=='date'">
            <label
              class="col-sm-2 col-form-label"
              for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <input
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="worklog[key]"
              >
            </div>
          </div>
          <div class="form-group row" v-if="key=='description'">
            <label
              class="col-sm-2 col-form-label"
              for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <textarea
                rows="10"
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="worklog[key]"
              ></textarea>
            </div>
            <div
              class="alert alert-danger col-sm-4 m-0 pt-0"
              role="alert"
              style="max-height:38px;line-height:38px;"
              v-show="inputGroup[index].warn"
            >{{inputGroup[index].warnContent}}</div>
          </div>
        </div>
      </form>
      <div class="m-75 mx-auto form-group row">
        <div class="col-sm-2"></div>
        <div class="col-sm-6 d-flex justify-content-around">
          <button class="btn btn-success col-5" @click="putIn">提交</button>
          <button class="btn btn-warning col-5" @click="putOff">放弃</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import tableNav from "../table-nav";
import req from "../../req";
import url from "../../url";
export default {
  name: "add-worklog",
  components: {
    tableNav
  },
  data() {
    return {
      isRight: true,
      inputGroup: [
        {
          title: "标题",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "日期",
          type: "date",
          warn: false,
          warnContent: ""
        },
        {
          title: "描述",
          type: "text",
          warn: false,
          warnContent: ""
        }
      ],
      worklog: {
        title: this.$store.state.worklog.title,
        date: "",
        description: this.$store.state.worklog.description,
        logtime: new Date().toLocaleDateString(),
        username: this.$store.state.enrollee,
        id:this.$store.state.enrollee
      }
    };
  },
  methods: {
    putIn() {
      if (false) {
        let year = this.schedule.date.slice(0, 5);
        let month = this.schedule.date.slice(6, 8);
        let day = this.schedule.date.slice(9, 11);
        this.schedule.year = year;
        this.schedule.month = month;
        this.schedule.day = day;
        req.POST(url.worklog.update, req.myUpdate(this.worklog)).then(data => {
          if (data.result == "success") {
            this.$router.push({
              path: this.$utils.getSuccessLink(),
              query: {
                message: "修改工作日志成功",
                local: this
              }
            });
          } else {
            this.$router.push({
              path: this.$utils.getFailureLink(),
              query: {
                message: "修改工作日志失败",
                local: this
              }
            });
          }
        });
      }
    },
    putOff() {
      this.$router.push(this.$utils.getPageLink(this));
    }
  }
};
</script>
<style scoped>
</style>
