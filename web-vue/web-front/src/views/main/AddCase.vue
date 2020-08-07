<template>
  <div class="w-100 my">
    <tableNav localName="新增案件" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="pt-4" style="width:100%; margin-left: 10px;padding-top:70px !important;">
      <a-tabs default-active-key="1" v-model="activeTab">
        <a-tab-pane key="1">
          <span slot="tab">
            <a-divider type="vertical" />
            选择案件类型
            <a-icon type="right" />
          </span>
          <a-checkable-tag class="tag-check" v-for="type in caseType" v-model="type.value" v-on:change="handleChange(type.key)">
            {{type.show}}
          </a-checkable-tag>
          <a-divider />
          <a-checkable-tag class="tag-check" v-for="child in caseChildType" v-model="child.value" v-on:change="handleChildChange(child.key)">
            {{child.show}}
          </a-checkable-tag>
        </a-tab-pane>
        <a-tab-pane key="2" ref="tab2" :disabled="tab2">
          <span slot="tab">
            <a-divider type="vertical" />
            利益冲突检查
            <a-icon type="right" />
          </span>
          <div id="components-form-demo-advanced-search">
            <a-form class="ant-advanced-search-form" :form="form" @submit="handleSearch">
              <a-row :gutter="24">
                <a-col :span="12">
                  <a-form-item label="委托人/当事人">
                    <a-input
                      v-decorator="[
                      `field-${0}`,
                      {
                        rules: [
                          {
                            required: true,
                            message: '请输入当事人',
                          },
                        ],
                      },
                    ]" placeholder="委托人/当事人"
                    />
                  </a-form-item>
                </a-col>

                <a-col :span="12">
                  <a-form-item label="对方当事人">
                    <a-input
                      v-decorator="[
                      `field-${1}`,
                      {
                        rules: [
                          {
                            required: true,
                            message: '请输入对方当事人',
                          },
                        ],
                      },
                    ]" placeholder="对方当事人"
                    />
                  </a-form-item>
                </a-col>
              </a-row>
              <a-row>
                <a-col :span="24" :style="{ textAlign: 'right' }">
                  <a-button type="primary" html-type="submit">
                    Search
                  </a-button>
                  <a-button :style="{ marginLeft: '8px' }" @click="handleReset">
                    Clear
                  </a-button>
                </a-col>
              </a-row>
            </a-form>
            <div class="search-result-list">
              <a-table :columns="columns" :data-source="data">
              </a-table>
            </div>
          </div>
        </a-tab-pane>
        <a-tab-pane key="3" :disabled="tab3">
          <span slot="tab">
            <a-divider type="vertical" />
            新增委托人
            <a-icon type="right" />
          </span>
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
        </a-tab-pane>
        <a-tab-pane key="4" :disabled="tab4">
          <span slot="tab">
            <a-divider type="vertical" />
            立案登记信息
            <a-icon type="right" />
          </span>
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
                          <a-input v-model="caseTable.caseNoFirst" />
                        </a-col>
                        <a-col :span="8">
                          <a-input v-model="caseTable.caseNoSecond" addon-before="(民)第" />
                        </a-col>
                      </a-row>
                    </a-input-group>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="案由" prop="reason">
                    <a-input v-model="caseTable.reason"/>
                  </a-form-model-item>
                </a-col>
              </a-row>
              <a-row :gutter="24">
                <a-col :span="12">
                  <a-form-model-item label="案件/业务性质" prop="nature">
                    <a-select v-model="caseTable.nature">
                      <a-select-option value="">请选择案件性质</a-select-option>
                      <a-select-option v-for="nature in natureCode" :value="nature.codeCode">{{nature.codeName}}</a-select-option>
                    </a-select>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="是否涉外" prop="foreigner">
                    <a-select v-model="caseTable.foreigner" >
                      <a-select-option v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                    </a-select>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="合同号" prop="contractNo">
                    <a-input v-model="caseTable.contractNo"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="委托人" prop="customName">
                    <a-input  v-model="caseTable.customName"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="对方当事人" prop="party">
                    <a-input v-model="caseTable.party"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="对方当事人地位" prop="partyStatus">
                    <a-input v-model="caseTable.partyStatus"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="我方当事人" >
                    <a-input v-model="caseTable.partyMy"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="收案日期" prop="time">
                    <a-date-picker v-model="caseTable.time" valueFormat="yyyy-MM-DD" :style="{width:'100%'}" placeholder="收案日期"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="受理部门" prop="department">
                    <a-input v-model="caseTable.department"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="诉讼标的" prop="lawsuit">
                    <a-input v-model="caseTable.lawsuit"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="代理费" prop="proxyAmount">
                    <a-input v-model="caseTable.proxyAmount"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="收费方式" prop="chargeMode">
                    <a-select v-model="caseTable.chargeMode">
                      <a-select-option value="">请选择收费方式</a-select-option>
                      <a-select-option v-for="payState in payStateCode" :value="payState.codeCode">{{payState.codeName}}</a-select-option>
                    </a-select>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="是否享受政府补贴" required>
                    <a-select v-model="caseTable.gov" >
                      <a-select-option v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                    </a-select>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="补助金额" prop="govAmount">
                    <a-input v-model="caseTable.govAmount"/>
                  </a-form-model-item>
                </a-col>
              </a-row>
              <a-row :gutter="24">
                <a-col :span="24">
                  <a-form-item label="诉讼阶段" :label-col="{span:2}" :wrapper-col="{span:22}" required>
                    <a-checkbox-group
                      v-model="caseTable.lawSuitPro"
                      :options="stageLabel"
                    />
                  </a-form-item>
                </a-col>
                <a-col :span="24">
                  <a-form-item label="诉讼地位" :label-col="{span:2}" :wrapper-col="{span:22}" required>
                    <a-radio-group name="lawSuit" v-model="caseTable.lawSuitStatus" >
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
                    <a-input v-model="caseTable.excluCaseNo"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="案源人">
                    <a-input v-model="caseTable.sourcer"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="第三人">
                    <a-input  v-model="caseTable.thirdParty"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="杂费">
                    <a-input  v-model="caseTable.incidental"/>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="是否指派">
                    <a-select v-model="caseTable.assign" >
                      <a-select-option v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                    </a-select>
                  </a-form-model-item>
                </a-col>
                <a-col :span="12">
                  <a-form-model-item label="地区">
                    <a-select :style="{width:'33%'}" v-model="caseTable.pro" @select="selectCity2">
                      <a-select-option value="">请选择省</a-select-option>
                      <a-select-option v-for="pro in proCode" :value="pro.classId">{{pro.className}}</a-select-option>
                    </a-select>
                    <a-select :style="{width:'33%'}" v-model="caseTable.city" @select="selectCountry">
                      <a-select-option value="">请选择市</a-select-option>
                      <a-select-option v-for="city in cityCode2" :value="city.classId">{{city.className}}</a-select-option>
                    </a-select>
                    <a-select :style="{width:'33%'}" v-model="caseTable.county">
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
                    <a-textarea v-model="caseTable.note" placeholder="请输入备注" :rows="4" />
                  </a-form-item>
                </a-col>
              </a-row>

              <a-row :gutter="24">
                <a-col style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
                  办案律师
                </a-col>
              </a-row>
              <a-row :gutter="24"
                     v-for="caseLawyer in caseTable.caseLawyerVos">
                <a-col :span="6">
                  <a-form-item>
                    <a-select v-model="caseLawyer.job">
                      <a-select-option v-for="lawyer in lawyerCode" :value="lawyer.codeCode">{{lawyer.codeName}}</a-select-option>
                    </a-select>
                  </a-form-item>
                </a-col>
                <a-col :span="6">
                  <a-form-item>
                    <a-auto-complete
                      :data-source="dataSource"
                      placeholder="律师姓名"
                      :filter-option="filterOption"
                    />
                  </a-form-item>
                </a-col>
              </a-row>
              <a-row>
                <a-col :span="24" :style="{ textAlign: 'left' }">
                  <a-button type="primary" html-type="submit" @click="addCase">
                    确定提交
                  </a-button>
                </a-col>
              </a-row>
            </a-form-model>
          </div>
        </a-tab-pane>
        <a-tab-pane key="5" :disabled="tab5">
          <span slot="tab">
            <a-divider type="vertical" />
            立案文书上传
            <a-icon type="right" />
          </span>
          Content of Tab Pane 2
        </a-tab-pane>
        <a-tab-pane key="6" :disabled="tab6">
          <span slot="tab">
            <a-divider type="vertical" />
            收案审批
            <a-icon type="right" />
          </span>
          Content of Tab Pane 2
        </a-tab-pane>
      </a-tabs>
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
            let child = true;
            req.GET("code/getCodesByType", {codeType: 'customState'}, function (response) {
                scope.$data.customStateCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'payState'}, function (response) {
                scope.$data.payStateCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'cus'}, function (response) {
                scope.$data.lawSuitCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'certType'}, function (response) {
                scope.$data.certTypeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'nation'}, function (response) {
                scope.$data.nationCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'sex'}, function (response) {
                scope.$data.sexCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'judge'}, function (response) {
                scope.$data.judgeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'national'}, function (response) {
                scope.$data.nationalCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'nature'}, function (response) {
                scope.$data.natureCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'edu'}, function (response) {
                scope.$data.eduCode = response.data.data;
            });
            req.GET("city/classType", {classType:1}, function (response) {
                scope.$data.proCode = response.data.data;
            });

            req.GET("code/getCodesByType", {codeType: 'customType'}, function (response) {
                scope.$data.customTypeCode = response.data.data;
            });

            req.GET("code/getCodesByType", {codeType: 'lawyer'}, function (response) {
                scope.$data.lawyerCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'stage'}, function (response) {
                let data = response.data.data;
                data.forEach(function (value) {
                    scope.$data.stageLabel.push({ label: value.codeName, value: value.codeCode})
                })
            });
            req.GET("city/classType", {classType:2}, function (response) {
                let data = response.data.data;
                data.forEach(function (value) {
                    if(scope.$data.cityParentCode[value.classParentId]){
                        scope.$data.cityParentCode[value.classParentId].push(value);
                    }else{
                        scope.$data.cityParentCode[value.classParentId] = [value];
                    }
                });
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
            });

            req.GET("code/getCodesByType", {codeType: 'trait'}, function (response) {
                let data = response.data.data;
                data.forEach(function (value) {
                    scope.$data.traitLabel.push({ label: value.codeName, value: value.codeCode})
                })
            });

            req.GET("case/main/init", null, function (response) {
                let data = response.data.data;
                scope.$data.caseTable = data;
            });

            req.GET("code/getCodesByType", {codeType: 'caseType'}, function (response) {
                scope.$data.caseTypes = response.data.data;
                scope.$data.caseTypes.forEach(function (value) {
                    scope.$data.caseType.push({key: value.codeCode, value: false, show: value.codeName});
                    if(child){
                        child = false;
                        req.GET("code/getCodesByType", {codeType: 'caseChildType'}, function (response) {
                            scope.$data.caseChildTypes = response.data.data;
                            scope.$data.caseChildTypes.forEach(function (value) {
                                if(scope.$data.tempTypes[value.parentCode]){
                                    scope.$data.tempTypes[value.parentCode].push({key:value.codeCode, value:false, show:value.codeName});
                                }else{
                                    scope.$data.tempTypes[value.parentCode] = [{key:value.codeCode, value:false, show:value.codeName}];
                                }
                            });
                            scope.$data.caseChildType = scope.$data.tempTypes[value.codeCode];
                        });
                    }
                });

            });
        },
        data() {
            return {
                current: 0,
                activeTab:'1',
                stepStyle: {
                    boxShadow: '0px -1px 0 0 #e8e8e8 inset',
                    marginLeft: '10px'
                },
                activeTabs:"1",
                caseType:[],
                caseTypes:[],
                caseChildType:[],
                caseChildTypes:[],
                tempTypes:{},
                case:{
                    type:''
                },
                lawers:[{
                    id:'',
                    type:'',
                    name:'',
                    amount:'',
                    amountType:''
                }],
                tab2:false,
                tab3:false,
                tab4:false,
                tab5:false,
                tab6:false,
                expand: false,
                form: this.$form.createForm(this, { name: 'advanced_search' }),
                form1: this.$form.createForm(this, { name: 'advanced_search1' }),
                data:[],
                columns:[{
                    title:'案号',
                    dataIndex:'caseNo',
                    width:'10%'
                },{
                    title:'委托人',
                    dataIndex:'bailor',
                    width:'10%'
                },{
                    title:'当事人/嫌疑人',
                    dataIndex:'party',
                    width:'10%'
                },{
                    title:'对方当事人',
                    dataIndex:'party1',
                    width:'10%'
                },{
                    title:'承办律师',
                    dataIndex:'userName',
                    width:'10%'
                },{
                    title:'案由',
                    dataIndex:'reason',
                    width:'10%'
                },{
                    title:'收案日期',
                    dataIndex:'time',
                    width:'10%'
                },{
                    title:'结案情况',
                    dataIndex:'state',
                    width:'10%'
                }],
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
                    reason:[{required:true, message:'案由不能为空', trigger: 'change'}],
                    nature:[{required:true, message:'案件性质不能为空', trigger: 'change'}],
                    foreigner:[{required:true, message:'是否涉外不能为空', trigger: 'select'}],
                    contractNo:[{required:true, message:'合同号不能为空', trigger: 'change'}],
                    customName:[{required:true, message:'委托人不能为空', trigger: 'change'}],
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
                caseTable:{
                    caseNoFirst:null,
                    caseNoSecond:null,
                    reason:null,
                    nature:null,
                    foreigner:null,
                    contractNo:null,
                    customId:null,
                    customName:null,
                    party:null,
                    partyStatus:null,
                    partyMy:null,
                    time:null,
                    department:null,
                    lawsuit:null,
                    proxyAmount:null,
                    chargeMode:null,
                    gov:null,
                    govAmount:null,
                    lawSuitPro:null,
                    lawSuitStatus:null,
                    excluCaseNo:null,
                    sourcer:null,
                    thirdParty:null,
                    incidental:null,
                    assign:null,
                    pro:null,
                    city:null,
                    county:null,
                    file:null,
                    note:null,
                    caseLawyerVos:[{
                        job:null,
                        lawyer:null,
                        amountType:null,
                        amount:null
                    }]
                }
            };
        },
        computed: {
            count() {
                return this.expand ? 11 : 7;
            }
        },
        methods: {
            putOff() {
                this.$router.push({name:'Pay'}, null);
            },
            handleChange(key) {
                let scope = this;
                scope.$data.caseType.forEach(function (value) {
                    if(value.key != key){
                        value.value = false;
                    }
                })
                scope.$data.caseChildType.forEach(function (value) {
                    value.value = false;
                })
                scope.$data.caseChildType = scope.$data.tempTypes[key];

            },
            handleChildChange(key){
                let scope = this;
                scope.$data.activeTab = '2';
                scope.$data.tab2 = false;

            },
            handleSearch(e) {
                e.preventDefault();
                this.form.validateFields((error, values) => {
                    console.log('error', error);
                    console.log('Received values of form: ', values);
                });
            },

            handleReset() {
                this.form.resetFields();
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
            selectCity2(){
                this.$data.caseTable.city = "";
                if(this.$data.caseTable.pro){
                    this.$data.cityCode2 = this.$data.cityParentCode[this.$data.caseTable.pro];
                }else{
                    this.$data.cityCode2 = [];
                }
            },
            selectCountry(){
                this.$data.caseTable.county = "";
                if(this.$data.caseTable.city){
                    this.$data.countryCode = this.$data.countryParentCode[this.$data.caseTable.city];
                }else{
                    this.$data.countryCode = [];
                }
            },
            uploadFile(data){

            },
            submit(){
                let scope = this;
                debugger;
                this.$refs['customRef'].validate(valid =>{
                    if(valid) {
                        req.POST("custom/insert", this.$data.custom, function (response) {
                            let scope = this;
                            scope.$data.activeTab = '4';
                            scope.$data.tab4 = false;
                        });
                    }
                });
            },
            addCase(){
                let scope = this;
                debugger;
                this.$refs['caseForm'].validate(valid =>{
                    if(valid) {
                        req.POST("/case/main/insert", this.$data.caseTable, function (response) {
                            let scope = this;
                            scope.$data.activeTab = '5';
                            scope.$data.tab5 = false;
                        });
                    }
                });
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
</style>
