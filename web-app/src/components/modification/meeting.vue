<template>
  <div class="w-100">
    <tableNav localName="修改收支" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <form class="m-75 mx-auto">
        <div class="pt-sm-3" v-for="(value,key,index) in meeting" :key="index">
          <div class="d-none" v-if="key=='id'"></div>
          <div class="form-group row" v-else-if="key!=='type' && key!=='payType'">
            <label
              class="col-sm-2 col-form-label"
              for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <input
                :readonly="key=='sender'"
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="meeting[key]"
              >
            </div>
            <div
              class="alert alert-danger col-sm-4 m-0 pt-0"
              role="alert"
              style="max-height:38px;line-height:38px;"
              v-show="inputGroup[index].warn"
            >{{inputGroup[index].warnContent}}</div>
          </div>
          <div class="form-group row" v-else-if="key=='type'">
            <label
                    class="col-sm-2 col-form-label"
                    for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <!--<textarea
                rows="10"
                id="exampleFormControlInput1"
                class="form-control"
                :type="inputGroup[index].type"
                v-model="meeting[key]"
              ></textarea>-->
              <a-select v-model="meeting.type">
                <a-select-option v-for="syscode in syscodes" :value="syscode.codeCode">
                  <span v-if="syscode.codeCode.length == 1">
                    {{syscode.codeName}}
                  </span>
                  <span v-if="syscode.codeCode.length == 2">
                      ------{{syscode.codeName}}
                  </span>
                </a-select-option>
              </a-select>
            </div>
            <div
                    class="alert alert-danger col-sm-4 m-0 pt-0"
                    role="alert"
                    style="max-height:38px;line-height:38px;"
                    v-show="inputGroup[index].warn"
            >{{inputGroup[index].warnContent}}</div>
          </div>
          <div class="form-group row" v-else-if="key=='payType'">
            <label
                    class="col-sm-2 col-form-label"
                    for="exampleFormControlInput1"
            >{{inputGroup[index].title}}</label>
            <div class="col-sm-6">
              <a-select v-model="meeting.payType">
                <a-select-option v-for="syscode in paySyscodes" :value="syscode.codeCode">
                  {{syscode.codeName}}
                </a-select-option>
              </a-select>
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
  name: "add-meeting",
  components: {
    tableNav,
    selectCode
  },
  data() {
    return {
      isRight: true,
      inputGroup: [
        {
          title: "收支类别",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "发生日期",
          type: "date",
          warn: false,
          warnContent: ""
        },
        {
          title: "收支金额",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "支付方式",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "案号",
          type: "text",
          warn: false,
          warnContent: ""
        },
        {
          title: "录入日期",
          type: "datetime-local",
          warn: false,
          warnContent: ""
        }
      ],
      meeting: {
        type: this.$store.state.meeting.type,
        time: this.$store.state.meeting.time,
        amount: this.$store.state.meeting.amount,
        payType:this.$store.state.meeting.payType,
        caseNo: this.$store.state.meeting.caseNo,
        createTime: this.$store.state.meeting.createTime,
        id: this.$store.state.meeting.id
      },
      syscodes:[{
        codeCode:'',
        codeName:''
      }],
      paySyscodes:[{
        codeCode:'',
        codeName:''
      }],
    };
  },
  mounted(){
    var scope = this;
    req.GET("code/getCodesByType", {codeType: "income"}).then(function (response) {
      scope.$data.syscodes = response.data;
    });
    req.GET("code/getCodesByType", {codeType: "payType"}).then(function (response) {
      scope.$data.paySyscodes = response.data;
    });
  },
  methods: {
    putIn() {
      if (this.isRight) {
        req.POST(url.meeting.update, this.meeting).then(data => {
            this.$router.push({
              path: this.$utils.getSuccessLink(),
              query: {
                message: "修改收支成功",
                local: this
              }
            });
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
