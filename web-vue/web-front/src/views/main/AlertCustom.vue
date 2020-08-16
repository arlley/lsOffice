<template>
  <div class="w-100">
    <tableNav localName="修改客户" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <a-page-header
      title="修改客户"
      @back="$router.go(-1)"
    />
    <div class="pt-4" style="width:100%; margin-left: 10px">
      <div class="components-form-demo-advanced-search">
        <a-form-model class="ant-advanced-search-form" :label-col="{span : 4}"
                      :wrapper-col="{span: 20}" :model="custom"
                      ref="customRef"
                      :rules="rules">
          <a-row :gutter="24">
            <a-col style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              基本信息
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="客户" ref="name" prop="name">
                <a-input v-model="custom.name"
                         placeholder="请输入客户名"
                         autocomplete="off"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="11">
              <a-form-model-item label="手机号码" ref="tel" prop="tel">
                <a-input v-model="custom.tel"
                         placeholder="请输入手机号" autocomplete="off"
                />
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="11">
              <a-form-model-item label="客户类型" ref="type" prop="type">
                <a-select v-model="custom.type">
                  <a-select-option value="">请选择客户类型</a-select-option>
                  <a-select-option v-for="customState in customStateCode" :value="customState.codeCode">{{customState.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-form-model-item ref="trait" prop="trait" label="身份特点" :label-col="{span:2}" :wrapper-col="{span:22}" required>
                <a-checkbox-group
                  v-model="custom.trait"
                  :options="traitLabel"
                />
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              其他选项
              <a :style="{ marginLeft: '8px', fontSize: '12px' }" @click="toggle">
                <span v-if="expand">收起</span><span v-else>展开</span> <a-icon :type="expand ? 'up' : 'down'" />
              </a>
            </a-col>
          </a-row>
          <a-row :gutter="24" v-show="expand">
            <a-col :span="8">
              <a-form-model-item label="证件类型">
                <a-select v-model="custom.certType">
                  <a-select-option value="">请选择证件类型</a-select-option>
                  <a-select-option v-for="certType in certTypeCode" :value="certType.codeCode">{{certType.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="8">
              <a-form-model-item label="证件号码">
                <a-input v-model="custom.certNo"
                         placeholder="请输入证件号码"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="8">
              <a-form-model-item label="民族">
                <a-select v-model="custom.nation">
                  <a-select-option value="">请选择民族</a-select-option>
                  <a-select-option v-for="nation in nationCode" :value="nation.codeCode">{{nation.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="性别">
                <a-select v-model="custom.sex">
                  <a-select-option value="">请选择性别</a-select-option>
                  <a-select-option v-for="sex in sexCode" :value="sex.codeCode">{{sex.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-item label="出生日期">
                <a-date-picker :style="{width:'100%'}" v-model="custom.birthday" placeholder="请选择出生日期"/>
              </a-form-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="国家和地区">
                <a-select v-model="custom.nation">
                  <a-select-option value="">请选择国家和地区</a-select-option>
                  <a-select-option v-for="national in nationalCode" :value="national.codeCode">{{national.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="文化程度">
                <a-select v-model="custom.edu">
                  <a-select-option value="">请选择文化程度</a-select-option>
                  <a-select-option v-for="edu in eduCode" :value="edu.codeCode">{{edu.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="6">
              <a-form-model-item label="省">
                <a-select v-model="custom.pro" @select="selectCity">
                  <a-select-option value="">请选择省</a-select-option>
                  <a-select-option v-for="pro in proCode" :value="pro.classId">{{pro.className}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="6">
              <a-form-model-item label="市">
                <a-select v-model="custom.city">
                  <a-select-option value="">
                    请选择城市
                  </a-select-option>
                  <a-select-option v-for="city in cityCode" :value="city.classId">{{city.className}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="户籍地">
                <a-input v-model="custom.domicile"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="24">
              <a-form-model-item label="详细地址/通讯地址" :label-col="{span:3}" :wrapper-col="{span:21}">
                <a-input v-model="custom.address"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="主要负责人">
                <a-input v-model="custom.principal"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="业务联系人">
                <a-input v-model="custom.businesser"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="邮箱">
                <a-input v-model="custom.email"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="职务">
                <a-input v-model="custom.duty"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="地区影响力">
                <a-input v-model="custom.influence"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="固定电话">
                <a-input v-model="custom.fixTel"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="客户状态" ref="type">
                <a-select v-model="custom.state">
                  <a-select-option value="">请选择客户状态</a-select-option>
                  <a-select-option v-for="customType in customTypeCode" :value="customType.codeCode">{{customType.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row v-show="expand">
            <a-col :span="12">
              <a-form-model-item label="法定代表人">
                <a-input v-model="custom.lawer"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="法定联系人">
                <a-input v-model="custom.lawerTel"
                />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="相关文件">
                <a-upload
                  :multiple="true"
                  :file-list="fileList"
                  :customRequest="uploadFile"
                >
                  <a-button> <a-icon type="upload" /> 上传文件 </a-button>
                </a-upload>
              </a-form-model-item>
            </a-col>
            <a-col :span="24">
              <a-form-model-item label="备注" :label-col="{span:2}" :wrapper-col="{span:22}">
                <a-textarea v-model="custom.note" placeholder="备注" :rows="4" />
              </a-form-model-item>
            </a-col>

          </a-row>
          <a-row>
            <a-col :span="24" :style="{ textAlign: 'left' }">
              <a-button type="primary" html-type="submit" @click="submit()">
                确定提交
              </a-button>
            </a-col>
          </a-row>
        </a-form-model>
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
            let scope = this;
            let id = scope.$route.query.id;
            req.GET("code/getCodesByType", {codeType: 'customState'}, function (response) {
                scope.$data.customStateCode = response.data.data;
                req.GET("code/getCodesByType", {codeType: 'certType'}, function (response) {
                    scope.$data.certTypeCode = response.data.data;
                    req.GET("code/getCodesByType", {codeType: 'nation'}, function (response) {
                        scope.$data.nationCode = response.data.data;
                        req.GET("code/getCodesByType", {codeType: 'sex'}, function (response) {
                            scope.$data.sexCode = response.data.data;
                            req.GET("code/getCodesByType", {codeType: 'national'}, function (response) {
                                scope.$data.nationalCode = response.data.data;
                                req.GET("code/getCodesByType", {codeType: 'edu'}, function (response) {
                                    scope.$data.eduCode = response.data.data;
                                    req.GET("city/classType", {classType:1}, function (response) {
                                        scope.$data.proCode = response.data.data;
                                        req.GET("code/getCodesByType", {codeType: 'customType'}, function (response) {
                                        scope.$data.customTypeCode = response.data.data;
                                        req.GET("city/classType", {classType:2}, function (response) {
                                            let data = response.data.data;
                                            data.forEach(function (value) {
                                                if(scope.$data.cityParentCode[value.classParentId]){
                                                    scope.$data.cityParentCode[value.classParentId].push(value);
                                                }else{
                                                    scope.$data.cityParentCode[value.classParentId] = [value];
                                                }
                                            });
                                            req.GET("code/getCodesByType", {codeType: 'trait'}, function (response) {
                                                let data = response.data.data;
                                                data.forEach(function (value) {
                                                    scope.$data.traitLabel.push({ label: value.codeName, value: value.codeCode})
                                                })
                                                req.GET("custom/getById", {id: id}, function (response) {
                                                    scope.$data.custom = response.data.data;
                                                    scope.$data.custom.trait = scope.$data.custom.trait.slice(2, -2).split(',');
                                                })
                                            });
                                        });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        },
        data() {
            return {
                expand:false,
                custom:{
                    name:null,
                    tel:null,
                    type:null,
                    trait:[],
                    certType:null,
                    certNo:null,
                    nation:null,
                    sex:null,
                    birthday:null,
                    edu:null,
                    pro:null,
                    city:null,
                    domicile:null,
                    address:null,
                    principal:null,
                    businesser:null,
                    email:null,
                    duty:null,
                    influence:null,
                    fixTel:null,
                    state:null,
                    lawer:null,
                    lawerTel:null,
                    filesPath:null,
                    note:null,
                },
                rules:{
                    name:[{required:true, message:'客户姓名不能为空', trigger: 'change'}],
                    tel:[{required:true, message:'电话不能为空', trigger: 'change'}],
                    type:[{required:true, message:'客户类型不能为空', trigger: 'select'}],
                    trait:[{required:true, message:'身份特点不能为空', trigger: 'change'}],
                },
                customTypeCode:[],
                traitLabel:[],
                certTypeCode:[],
                nationCode:[],
                sexCode:[],
                nationalCode:[],
                eduCode:[],
                proCode:[],
                cityCode:[],
                cityParentCode:[],
                customStateCode:[],
                fileList: []
            }
        },
        methods: {
            putOff() {
                this.$router.push({name:'Pay'}, null);
            },
            toggle() {
                this.expand = !this.expand;
            },
            selectCity(){
                this.$data.custom.city = "";
                if(this.$data.custom.pro){
                    this.$data.cityCode = this.$data.cityParentCode[this.$data.custom.pro];
                }else{
                    this.$data.cityCode = [];
                }
            },
            handleChange(info) {
                let fileList = [...info.fileList];

                // 1. Limit the number of uploaded files
                //    Only to show two recent uploaded files, and old ones will be replaced by the new
                fileList = fileList.slice(-2);

                // 2. read from response and show file link
                fileList = fileList.map(file => {
                    if (file.response) {
                        // Component will show file.url as link
                        file.url = file.response.url;
                    }
                    return file;
                });

                this.fileList = fileList;
            },
            uploadFile(data){

            },
            submit(){
                let scope = this;
                this.$refs['customRef'].validate(valid =>{
                    if(valid) {
                        req.POST("custom/update", this.$data.custom, function (response) {
                            scope.$router.push({
                                name: 'Success',
                                query: {
                                    message: "修改客户成功",
                                    local: "Custom"
                                }
                            });
                        });
                    }
                });
            }
        }
    };
</script>
<style scoped>
  .components-form-demo-advanced-search .ant-form {
    max-width: none;
  }
  .components-form-demo-advanced-search .search-result-list {
    margin-top: 16px;
    border: 1px dashed #e9e9e9;
    border-radius: 6px;
    background-color: #fafafa;
    min-height: 200px;
    text-align: center;
    padding-top: 80px;
  }
  .ant-col{
  }
  .my{
    max-height: 100%;
  }
</style>
