<template>
  <div class="w-100">
    <tableNav localName="修改人员" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="width:800px">
      <a-form-model ref="ruleForm" :model="address" :rules="rules" v-bind="layout">
        <div class="pt-sm-3">
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="姓名" prop="name">
                <a-input v-model="address.name" type="text" autocomplete="off" />
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="电话" prop="tel">
                <a-input v-model="address.tel" type="text" autocomplete="off" />
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="状态" prop="state">
                <a-select v-model="address.state" placeholder="请选择状态">
                  <a-select-option value="1">
                    在职
                  </a-select-option>
                  <a-select-option value="0">
                    离职
                  </a-select-option>
                </a-select>
              </a-form-model-item>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-9 col-sm-offset-2">
              <a-form-model-item has-feedback label="密码" prop="password">
                <a-input v-model="address.password" type="password" autocomplete="off" />
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
            let id = this.$route.query.id;
            let scope = this;
            req.GET("user/getUser", {'id': id}, function (response) {
                scope.$data.address = response.data.data;
            })
        },
        data() {
            return {
                isRight: true,
                rules:{
                    name:[{required:true, message:'用户姓名为空', trigger: 'change'}],
                    password:[{required:true, message:'密码不能为空', trigger: 'change'}],
                    tel:[{required:true, message:'用户名不能为空', trigger: 'change'},
                        { validator: this.$verify.formatTel, trigger: 'change' }],
                    state:[{required:true, message:'密码不能为空', trigger: 'change'}],
                },
                layout: {
                    labelCol: { span: 4 },
                    wrapperCol: { span: 14 },
                },
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
                let scope = this;
                this.$refs['ruleForm'].validate(valid => {
                    req.POST('user/update', this.address, function (response) {
                        scope.$router.push({
                            name: 'Success',
                            query: {
                                message: "修改人员成功",
                                local: "User"
                            }
                        });
                    });
                });
            },
            putOff() {
                this.$router.push({name:'User'}, null);
            }
        }
    };
</script>
<style scoped>
</style>
