<template>
  <div class="w-100">
    <tableNav localName="添加人员" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <form class="m-75 mx-auto">
        <div class="pt-sm-3" v-for="(value,key,index) in address" :key="index">
          <div class="form-group row" v-if="key=='state'">
            <label class="col-sm-2 col-form-label" for>{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
<!--              <select class="form-control" id v-model="address.state">-->
<!--                <option value="1">在职</option>-->
<!--                <option value="0">离职</option>-->
<!--              </select>-->
              <selectCode v-model="address[key]" codeType="state">

              </selectCode>
            </div>
            <div
              class="alert alert-danger col-sm-4 m-0 pt-0"
              role="alert"
              style="max-height:38px;line-height:38px;"
              v-show="inputGroup[index].warn"
            >{{inputGroup[index].warnContent}}</div>
          </div>
          <div class="form-group row" v-else-if="key!='username'">
            <label
              class="col-sm-2 col-form-label"
              for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <input
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="address[key]"
              >
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
import selectCode from "../select-code"
export default {
  name: "add-address",
  components: {
    tableNav, selectCode
  },
  data() {
    return {
      isRight: true,
      inputGroup: [
        {
          title: "姓名",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "电话",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "状态",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "密码",
          type: "password",
          warn: false,
          warnContent: ""
        }
      ],
      address: {
        name: "",
        tel: "",
        state: "",
        password:""
      }
    };
  },
  methods: {
    putIn() {
      if (this.isRight) {
        req.POST(url.address.insert, this.address).then(data => {
            this.$router.push({
              path: this.$utils.getSuccessLink(),
              query: {
                message: "增加人员成功",
                local: this
              }
            });
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
