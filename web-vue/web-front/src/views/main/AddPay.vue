<template>
  <div class="w-100">
    <tableNav localName="添加收支" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <a-form-model ref="payForm" :model="pay" :rules="rules" v-bind="layout">
        <div class="pt-sm-3">
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="收入类别" prop="type">
                <a-select v-model="pay.type" placeholder="请选择收支类别">
                  <a-select-option v-for="sys in payScope"  :value="sys.codeCode">
                    <span v-if="sys.codeCode.length == 1">{{sys.codeName}}</span>
                    <span v-if="sys.codeCode.length > 1">-----------{{sys.codeName}}</span>
                  </a-select-option>
                </a-select>
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="收支时间" prop="time">
                <a-date-picker format = "YYYY-MM-DD" mode="date" @change="onChange" placeholder="请选择收支时间" style="width:100%; min-width: 100%"/>
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="收支金额" prop="amount">
                <a-input v-model="pay.amount"  autocomplete="off" />
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="支付方式" prop="payType">
                <a-select v-model="pay.payType" placeholder="请选择支付方式">
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
              <a-form-model-item has-feedback label="案号" prop="caseNo">
                <a-input v-model="pay.caseNo"  autocomplete="off" />
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
                    type:[{required:true, message:'收支类别不能为空', trigger: 'change'}],
                    time:[{required:true, message:'收支时间不能为空', trigger: 'change'}],
                    amount:[{required:true, message:'收支金额不能为空', trigger: 'change'},
                        { validator: this.$verify.formatNum, trigger: 'change' }],
                    payType:[{required:true, message:'收支方式不能为空', trigger: 'change'}],
                    caseNo:[{required:true, message:'案号不能为空', trigger: 'change'}],
                },
                layout: {
                    labelCol: { span: 4 },
                    wrapperCol: { span: 14 },
                },
                pay: {
                    type: "",
                    time: "",
                    amount: "",
                    payType:"",
                    caseNo:""
                },
                payScope:[],
                payTypeScope:[]
            };
        },
        methods: {
            putIn() {
                let scope = this;
                this.$refs['payForm'].validate(valid => {
                    req.POST('pay/insert', this.pay, function (response) {
                        scope.$router.push({
                            name: 'Success',
                            query: {
                                message: "增加收支成功",
                                local: "Pay"
                            }
                        });
                    });
                });
            },
            putOff() {
                this.$router.push({name:'Pay'}, null);
            },
            onChange:function (date, dateString) {
                this.$data.pay.time = dateString;
            }
        }
    };
</script>
<style scoped>
</style>
