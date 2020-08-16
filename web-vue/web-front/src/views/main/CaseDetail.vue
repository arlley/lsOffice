<template>
  <div class="w-100">
    <tableNav localName="案件详情" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="mx-auto pt-4" style="padding-top:70px !important">
      <div class="components-form-demo-advanced-search">
        <a-form-model
          ref="caseForm"
          :model="caseTable"
          :label-col="{span:4}"
          :wrapper-col="{span:20}"
          :rules="rules"
        >
          <a-row :gutter="24">
            <a-col style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              基本信息
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="案号" required>
                <a-input-group size="large">
                  <a-row :gutter="8">
                    <a-col :span="5">
                      <a-input disabled="true" v-model="caseTable.caseNoFirst"/>
                    </a-col>
                    <a-col :span="8">
                      <a-input disabled="true" v-model="caseTable.caseNoSecond" addon-before="(民)第" />
                    </a-col>
                  </a-row>
                </a-input-group>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="案由" prop="reason">
                <a-input disabled="true" v-model="caseTable.reason"/>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="案件/业务性质" prop="nature">
                <a-select v-model="caseTable.nature" disabled="true">
                  <a-select-option value="">请选择案件性质</a-select-option>
                  <a-select-option v-for="nature in natureCode" :value="nature.codeCode">{{nature.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="是否涉外" prop="foreigner">
                <a-select disabled="true" v-model="caseTable.foreigner" >
                  <a-select-option v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="合同号" prop="contractNo">
                <a-input disabled="true" v-model="caseTable.contractNo"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="委托人">
                <a-input disabled="true" />
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="对方当事人" prop="party">
                <a-input disabled="true" v-model="caseTable.party"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="对方当事人地位" prop="partyStatus">
                <a-input disabled="true" v-model="caseTable.partyStatus"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="我方当事人" >
                <a-input disabled="true" v-model="caseTable.partyMy"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="收案日期" prop="time">
                <a-date-picker disabled="true" v-model="caseTable.time" valueFormat="yyyy-MM-DD" :style="{width:'100%'}" placeholder="收案日期"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="受理部门" prop="department">
                <a-input disabled="true" v-model="caseTable.department"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="诉讼标的" prop="lawsuit">
                <a-input disabled="true" v-model="caseTable.lawsuit"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="代理费" prop="proxyAmount">
                <a-input disabled="true" v-model="caseTable.proxyAmount"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="收费方式" prop="chargeMode">
                <a-select disabled="true" v-model="caseTable.chargeMode">
                  <a-select-option value="">请选择收费方式</a-select-option>
                  <a-select-option v-for="payState in payStateCode" :value="payState.codeCode">{{payState.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="是否享受政府补贴" required>
                <a-select disabled="true" v-model="caseTable.gov" >
                  <a-select-option v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="补助金额" prop="govAmount">
                <a-input disabled="true" v-model="caseTable.govAmount"/>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-form-item label="诉讼阶段" :label-col="{span:2}" :wrapper-col="{span:22}" required>
                <a-checkbox-group disabled=true
                  v-model="caseTable.lawSuitPro"
                  :options="stageLabel"
                />
              </a-form-item>
            </a-col>
            <a-col :span="24">
              <a-form-item label="诉讼地位" :label-col="{span:2}" :wrapper-col="{span:22}" required>
                <a-radio-group name="lawSuit" disabled="true" v-model="caseTable.lawSuitStatus" >
                  <a-radio v-for = "lawSuit in lawSuitCode" :value="lawSuit.codeCode">
                    {{lawSuit.codeName}}
                  </a-radio>
                </a-radio-group>
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              其他选填
              <a :style="{ marginLeft: '8px', fontSize: '12px' }" @click="toggle">
                <span v-if="expand">收起</span><span v-else>展开</span> <a-icon :type="expand ? 'up' : 'down'" />
              </a>
            </a-col>
          </a-row>
          <a-row v-show="expand">
            <a-col :span="12">
              <a-form-model-item label="专属案号">
                <a-input disabled="true" v-model="caseTable.excluCaseNo"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="案源人">
                <a-input disabled="true" v-model="caseTable.sourcer"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="第三人">
                <a-input disabled="true"  v-model="caseTable.thirdParty"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="杂费">
                <a-input disabled="true"  v-model="caseTable.incidental"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="是否指派">
                <a-select disabled="true" v-model="caseTable.assign" >
                  <a-select-option v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="地区">
                <a-select disabled="true" :style="{width:'33%'}" v-model="caseTable.pro" >
                  <a-select-option value="">请选择省</a-select-option>
                  <a-select-option v-for="pro in proCode" :value="pro.classId">{{pro.className}}</a-select-option>
                </a-select>
                <a-select disabled="true" :style="{width:'33%'}" v-model="caseTable.city">
                  <a-select-option value="">请选择市</a-select-option>
                  <a-select-option v-for="city in cityCode2" :value="city.classId">{{city.className}}</a-select-option>
                </a-select>
                <a-select disabled="true" :style="{width:'33%'}" v-model="caseTable.county">
                  <a-select-option value="">请选择县</a-select-option>
                  <a-select-option v-for="country in countryCode" :value="country.classId">{{country.className}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-item label="相关文件">
                <a-upload
                  name="file"
                  :multiple="true"
                  action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                >
                  <a-button> <a-icon type="upload" /> 上传文件 </a-button>
                </a-upload>
              </a-form-item>
            </a-col>
          </a-row>
          <a-row v-show="expand">
            <a-col :span="24">
              <a-form-item label="备注" :label-col="{span:2}" :wrapper-col="{span:22}">
                <a-textarea disabled="true" v-model="caseTable.note" placeholder="请输入备注" :rows="4" />
              </a-form-item>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="24" :style="{ textAlign: 'left' }">
              <a-button type="primary" @click="audit">
                审核
              </a-button>
              <a-drawer
                title="审核"
                placement="right"
                closable="false"
                :visible="auditV"
                @close="onClose"
              >
                <a-select v-model="auditVResult" style="width: 200px">
                  <a-select-option value="1">审核通过</a-select-option>
                  <a-select-option value="2">审核审核退回</a-select-option>
                </a-select>
                <a-button style="margin-top: 10px" @click="auditSub">提交</a-button>
              </a-drawer>
              <a-button type="primary" @click="payee">
                收款
              </a-button>
              <a-drawer
                title="收款"
                placement="right"
                closable="false"
                :visible="PayV"
                @close="onClosePay"
              >
                <a-input v-model="payVResult" style="min-width: 200px" placeholder="请输入收款金额"/>
                <a-button style="margin-top: 10px" @click="paySub">提交</a-button>
              </a-drawer>
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
            scope.$data.caseType=[];
            req.GET("code/getCodesByType", {codeType: 'customState'}, function (response) {
                scope.$data.customStateCode = response.data.data;
                req.GET("code/getCodesByType", {codeType: 'payState'}, function (response) {
                    scope.$data.payStateCode = response.data.data;
                    req.GET("code/getCodesByType", {codeType: 'cus'}, function (response) {
                        scope.$data.lawSuitCode = response.data.data;
                        req.GET("code/getCodesByType", {codeType: 'certType'}, function (response) {
                            scope.$data.certTypeCode = response.data.data;
                            req.GET("code/getCodesByType", {codeType: 'nation'}, function (response) {
                                scope.$data.nationCode = response.data.data;
                                req.GET("code/getCodesByType", {codeType: 'sex'}, function (response) {
                                    scope.$data.sexCode = response.data.data;
                                    req.GET("code/getCodesByType", {codeType: 'judge'}, function (response) {
                                        scope.$data.judgeCode = response.data.data;
                                        req.GET("code/getCodesByType", {codeType: 'national'}, function (response) {
                                            scope.$data.nationalCode = response.data.data;
                                            req.GET("code/getCodesByType", {codeType: 'nature'}, function (response) {
                                                scope.$data.natureCode = response.data.data;
                                                req.GET("city/classType", {classType:1}, function (response) {
                                                    scope.$data.proCode = response.data.data;
                                                    req.GET("code/getCodesByType", {codeType: 'customType'}, function (response) {
                                                        scope.$data.customTypeCode = response.data.data;
                                                        req.GET("code/getCodesByType", {codeType: 'amountType'}, function (response) {
                                                            scope.$data.amountTypeCode = response.data.data;
                                                            req.GET("code/getCodesByType", {codeType: 'lawyer'}, function (response) {
                                                                scope.$data.lawyerCode = response.data.data;
                                                                req.GET("code/getCodesByType", {codeType: 'stage'}, function (response) {
                                                                    let data = response.data.data;
                                                                    data.forEach(function (value) {
                                                                        scope.$data.stageLabel.push({ label: value.codeName, value: value.codeCode})
                                                                    })
                                                                    req.GET("city/classType", {classType:2}, function (response) {
                                                                        let data = response.data.data;
                                                                        data.forEach(function (value) {
                                                                            if(scope.$data.cityParentCode[value.classParentId]){
                                                                                scope.$data.cityParentCode[value.classParentId].push(value);
                                                                            }else{
                                                                                scope.$data.cityParentCode[value.classParentId] = [value];
                                                                            }
                                                                        });
                                                                        req.GET("city/classType", {classType:3}, function (response) {
                                                                            let data = response.data.data;
                                                                            data.forEach(function (value) {
                                                                                if(scope.$data.countryParentCode[value.classParentId]){
                                                                                    scope.$data.countryParentCode[value.classParentId].push(value);
                                                                                }else{
                                                                                    scope.$data.countryParentCode[value.classParentId] = [value];
                                                                                }
                                                                            });
                                                                            req.GET("code/getCodesByType", {codeType: 'trait'}, function (response) {
                                                                                let data = response.data.data;
                                                                                data.forEach(function (value) {
                                                                                    scope.$data.traitLabel.push({ label: value.codeName, value: value.codeCode})
                                                                                })
                                                                                req.GET("code/getCodesByType", {codeType: 'income'}, function (response) {
                                                                                    let result = response.data.data;
                                                                                    result.forEach(function (value, index) {
                                                                                        scope.syscodesCheck[value.codeCode] = value.codeName;
                                                                                    })
                                                                                    req.GET("code/getCodesByType", {codeType: 'payType'}, function (response) {
                                                                                        let result = response.data.data;
                                                                                        result.forEach(function (value, index) {
                                                                                            scope.PaySyscodesCheck[value.codeCode] = value.codeName;
                                                                                        })
                                                                                        req.GET("/case/main/detail", {id: scope.$route.query.id}, function (response) {
                                                                                            scope.$data.caseTable = response.data.data;
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
                auditV:false,
                PayV:false,
                auditVResult:null,
                payVResult:null,
                rules:{
                    name:[{required:true, message:'客户姓名不能为空', trigger: 'change'}],
                    tel:[{required:true, message:'电话不能为空', trigger: 'change'}],
                    type:[{required:true, message:'客户类型不能为空', trigger: 'select'}],
                    trait:[{required:true, message:'身份特点不能为空', trigger: 'change'}],
                    reason:[{required:true, message:'案由不能为空', trigger: 'change'}],
                    nature:[{required:true, message:'案件性质不能为空', trigger: 'change'}],
                    foreigner:[{required:true, message:'是否涉外不能为空', trigger: 'select'}],
                    contractNo:[{required:true, message:'合同号不能为空', trigger: 'change'}],
                    party:[{required:true, message:'对方当事人不能为空', trigger: 'change'}],
                    partyStatus:[{required:true, message:'对方当事人地位不能为空', trigger: 'change'}],
                    time:[{required:true, message:'收案时间不能为空', trigger: 'change'}],
                    department:[{required:true, message:'受理部门不能为空', trigger: 'change'}],
                    lawsuit:[{required:true, message:'诉讼标的不能为空', trigger: 'change'}],
                    proxyAmount:[{required:true, message:'代理费不能为空', trigger: 'change'}],
                    chargeMode:[{required:true, message:'收费方式不能为空', trigger: 'change'}],
                    govAmount:[{required:true, message:'补贴金额不能为空', trigger: 'change'}],
                    gov:[{required:true, message:'是否享受政府补贴不能为空', trigger: 'change'}],
                },
                syscodesCheck:{},
                PaySyscodesCheck:{},
                customTypeCode:[],
                traitLabel:[],
                certTypeCode:[],
                nationCode:[],
                sexCode:[],
                nationalCode:[],
                eduCode:[],
                proCode:[],
                cityCode:[],
                cityCode2:[],
                cityParentCode:[],
                countryCode:[],
                countryParentCode:[],
                customStateCode:[],
                judgeCode:[],
                fileList: [],
                natureCode:[],
                payStateCode:[],
                stageLabel:[],
                lawSuitCode:[],
                lawyerCode:[],
                amountTypeCode:[],
                caseTable: {
                    caseNoFirst: null,
                    caseNoSecond: null,
                    reason: null,
                    nature: null,
                    foreigner: null,
                    contractNo: null,
                    customId: null,
                    customName: null,
                    party: null,
                    partyStatus: null,
                    partyMy: null,
                    time: null,
                    department: null,
                    lawsuit: null,
                    proxyAmount: null,
                    chargeMode: null,
                    gov: null,
                    govAmount: null,
                    lawSuitPro: null,
                    lawSuitStatus: null,
                    excluCaseNo: null,
                    sourcer: null,
                    thirdParty: null,
                    incidental: null,
                    assign: null,
                    pro: null,
                    city: null,
                    county: null,
                    file: null,
                    note: null,
                    caseLawyerVos: [{
                        job: null,
                        lawyer: null,
                        amountType: null,
                        amount: null
                    }]
                }
            };
        },
        methods: {
            putOff() {
                this.$router.push({name:'Pay'}, null);
            },
            audit(){
                this.$data.auditV = true;
            },
            auditSub(){
                let scope = this;
                let name = localStorage.getItem("name");
                req.GET("/case/main/audit", {id: this.$route.query.id, auditRes: this.$data.auditVResult, name:name}, function (response) {
                    scope.$router.push({
                        name: 'Success',
                        query: {
                            message: "审核案件成功",
                            local: "Case"
                        }
                    });
                })
            },
            paySub(){
                let scope = this;
                let lawyerId = localStorage.getItem("id");
                req.GET("/case/main/pay", {id: this.$route.query.id, payVResult: this.$data.payVResult, lawyerId:lawyerId}, function (response) {
                    scope.$router.push({
                        name: 'Success',
                        query: {
                            message: "收款成功",
                            local: "Case"
                        }
                    });
                })
            },
            onClose(){
                this.$data.auditV = false;
            },
            onClosePay(){
                this.$data.PayV = false;
            },
            payee(){
                this.$data.PayV  = true;
            },
            toggle() {
                this.expand = !this.expand;
            }
        }
    };
</script>
<style scoped>

  .tag-check{
    border: 1px solid #aaaaaa;
    min-width: 150px;
    height: 50px;
    font-size: 20px;
    text-align: center;
    vertical-align:middle;
    margin-top: 10px;
    padding: 10px;
  }
  .ant-advanced-search-form {
    padding: 24px;
    background: #fbfbfb;
    border: 1px solid #d9d9d9;
    border-radius: 6px;
  }

  .ant-advanced-search-form .ant-form-item {
    display: flex;
  }

  .ant-advanced-search-form .ant-form-item-control-wrapper {
    flex: 1;

  }

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

  .certain-category-search-dropdown .ant-select-dropdown-menu-item-group-title {
    color: #666;
    font-weight: bold;
  }

  .certain-category-search-dropdown .ant-select-dropdown-menu-item-group {
    border-bottom: 1px solid #f6f6f6;
  }

  .certain-category-search-dropdown .ant-select-dropdown-menu-item {
    padding-left: 16px;
  }

  .certain-category-search-dropdown .ant-select-dropdown-menu-item.show-all {
    text-align: center;
    cursor: default;
  }

  .certain-category-search-dropdown .ant-select-dropdown-menu {
    max-height: 300px;
  }
  .certain-category-search-wrapper
  >>> .certain-category-search.ant-select-auto-complete
  .ant-input-affix-wrapper
  .ant-input-suffix {
    right: 12px;
  }
  .certain-category-search-wrapper >>> .certain-search-item-count {
    position: absolute;
    color: #999;
    right: 16px;
  }
  .certain-category-search-wrapper
  >>> .certain-category-search.ant-select-focused
  .certain-category-icon {
    color: #108ee9;
  }
  .certain-category-search-wrapper >>> .certain-category-icon {
    color: #6e6e6e;
    transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
    font-size: 16px;
  }
</style>
