<template>
  <div class="w-100">
    <tableNav localName="消息" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <form class="m-75 mx-auto">
        <div class="pt-sm-3" v-for="(value,key,index) in message" :key="index">
          <div class="form-group row" v-if="key=='username'">
            <label class="col-sm-2 col-form-label" for>{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <select class="form-control" id v-model="message.username">
                <option
                  v-for="(item,index) in users"
                  :key="index"
                  :value="item.username"
                >{{item.username}}</option>
              </select>
            </div>
            <div
              class="alert alert-danger col-sm-4 m-0 pt-0"
              role="alert"
              style="max-height:38px;line-height:38px;"
              v-show="inputGroup[index].warn"
            >{{inputGroup[index].warnContent}}</div>
          </div>
          <div class="form-group row" v-else-if="key=='message'">
            <label
              class="col-sm-2 col-form-label"
              for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <textarea rows="10"
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="message[key]"
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
  name: "add-message",
  components: {
    tableNav
  },
  data() {
    return {
      isRight: true,
      users:this.$store.state.users,
      message: {
        username: "",
        message: "",
        sender: this.$store.state.enrollee
      },
      inputGroup: [
        {
          title: "接受人",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "信息内容",
          type: "text",
          warn: false,
          warnContent: ""
        }
      ]
    };
  },
  methods: {
    putIn() {
      console.log(this.message.username);
      console.log(this.message.message);
      if (this.isRight) {
        req.POST(url.message.insert, req.myInsert(this.message)).then(data => {
          if (data.result == "success") {
            this.$router.push({
              path: this.$utils.getSuccessLink(),
              query: {
                message: "发送信息成功",
                local: this
              }
            });
          } else {
            this.$router.push({
              path: this.$utils.getFailureLink(),
              query: {
                message: "发送信息失败",
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
