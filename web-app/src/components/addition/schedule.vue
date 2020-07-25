<template>
  <div class="w-100">
    <tableNav localName="添加行程" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <form class="m-75 mx-auto">
        <div class="pt-sm-3" v-for="(value,key,index) in schedule" :key="index">
          <div class="form-group row" v-if="key=='date'">
            <label
              class="col-sm-2 col-form-label"
              for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <input
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="schedule[key]"
              >
            </div>
          </div>
          <div class="form-group row" v-else-if="key=='plan'">
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
                v-model="schedule[key]"
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
  name: "add-schedule",
  components: {
    tableNav
  },
  data() {
    return {
      isRight: true,
      schedule: {
        date: "",
        plan: "",
        year: "",
        month: "",
        day: "",
        username: this.$store.state.enrollee
      },
      inputGroup: [
        {
          title: "计划时间",
          type: "date",
          warn: false,
          warnContent: ""
        },
        {
          title: "计划内容",
          type: "text",
          warn: false,
          warnContent: ""
        }
      ]
    };
  },
  methods: {
    putIn() {
      if (this.isRight) {
        let year = this.schedule.date.slice(0, 4);
        let month = this.schedule.date.slice(5, 7);
        let day = this.schedule.date.slice(8, 10);
        this.schedule.year = year;
        this.schedule.month = month;
        this.schedule.day = day;
        req
          .POST(url.schedule.insert, req.myInsert(this.schedule))
          .then(data => {
            if (data.result == "success") {
              this.$router.push({
              path: this.$utils.getSuccessLink(),
              query: {
                message: "添加行程成功",
                local: this
              }
            });
            } else {
              this.$router.push({
              path: this.$utils.getFailureLink(),
              query: {
                message: "添加行程失败",
                local: this
              }
            });
            }
          });
      }
    },
    putOff() {
      this.$router.push(this.$utils.getPageLink(this.$options.name));
    }
  }
};
</script>
<style scoped>
</style>
