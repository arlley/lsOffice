<template>
  <div class="w-100">
    <tableNav localName="添加个人支出" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px;padding-top:70px !important">
      <a-form-model ref="payForm" :model="pay" :rules="rules" v-bind="layout">
        <div class="pt-sm-3">
          <div class="row">
            <div class="col-sm-1"></div>
          </div>
          <div class="row" v-if="pay.payType == '3'">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="类别手填" prop="payType">
                <a-input v-if="pay.payType == '3'" v-model="pay.payTypeDetail"/>
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="支出时间" prop="payTime">
                <a-date-picker v-model="pay.payTime" format="YYYY-MM-DD" valueFormat = "YYYY-MM-DD" mode="date" @change="onChange" placeholder="请选择收支时间" style="width:100%; min-width: 100%"/>
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="支出金额" prop="payAmount">
                <a-input v-model="pay.payAmount"  autocomplete="off" />
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="支付方式" prop="payType">
                <a-select v-model="pay.payWay" placeholder="请选择支付方式">
                  <a-select-option v-for="sys in payTypeScope" :value="sys.codeCode">
                    {{sys.codeName}}
                  </a-select-option>
                </a-select>
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="备注">
                <a-textarea v-model="pay.note"  autocomplete="off" />
              </a-form-model-item>
            </div>
          </div>
        </div>
      </a-form-model>
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
    import tableNav from "../../components/TableNav";
    import req from "../../req";
    export default {
        name: "add-address",
        components: {
            tableNav
        },
        mounted(){
            let scope = this.$data;
            req.GET("code/getCodesByType", {codeType: 'income'}, function (response) {
                scope.payScope = response.data.data;

            });
            req.GET("code/getCodesByType", {codeType: 'payType'}, function (response) {
                scope.payTypeScope = response.data.data;
            });
        },
        data() {
            return {
                isRight: true,
                rules:{
                    payType:[{required:true, message:'支出类别不能为空', trigger: 'change'}],
                    payTime:[{required:true, message:'支出时间不能为空', trigger: 'change'}],
                    payAmount:[{required:true, message:'支出金额不能为空', trigger: 'change'},
                        { validator: this.$verify.formatNum, trigger: 'change' }],
                    payWay:[{required:true, message:'支出方式不能为空', trigger: 'change'}],
                },
                layout: {
                    labelCol: { span: 4 },
                    wrapperCol: { span: 14 },
                },
                pay: {
                    payType: "3",
                    payTypeDetail: "",
                    payTime: "",
                    payAmount:"",
                    payWay:"",
                    note:"",
                    lawyerId: localStorage.getItem("id")
                },
                payScope:[],
                payTypeScope:[]
            };
        },
        methods: {
            putIn() {
                let scope = this;
                this.$refs['payForm'].validate(valid => {
                    req.POST('pay/detail/insertLawyerMyOut', this.pay, function (response) {
                        scope.$router.push({
                            name: 'Success',
                            query: {
                                message: "增加个人支出成功",
                                local: "payDetail"
                            }
                        });
                    });
                });
            },
            putOff() {
                this.$router.push({name:'payDetail'}, null);
            },
            onChange:function (date, dateString) {
                this.$data.pay.time = dateString;
            }
        }
    };
</script>
<style scoped>
</style>
