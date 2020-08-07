<template>
  <div>
    <tableNav
      localName="人事/编辑律师资料"
    ></tableNav>
    <a-page-header
      title="添加员工"
      @back="$router.go(-1)"
    />
    <div class="pt-4" style="width:100%; margin-left: 10px; ">
      <div class="components-form-demo-advanced-search">
        <a-form-model class="ant-advanced-search-form" :label-col="{span : 4}"
                      :wrapper-col="{span: 20}" :model="lawyer"
                      ref="lawyerRef"
                      :rules="rules">
          <a-row :gutter="24">
            <a-col style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              基本信息
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="姓名" prop="name">
                <a-input v-model="lawyer.name"/>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="出生日期" prop="birthday">
                <a-date-picker valueFormat="YYYY-MM-DD" v-model="lawyer.birthday" :style="{'width':'100%'}" placeholder="请选择出生日期"/>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="性别" prop="sex">
                <a-radio-group v-model="lawyer.sex">
                  <a-radio v-for="sex in sexCode" :value="sex.codeCode">{{sex.codeName}}</a-radio>
                </a-radio-group>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="是否在职" prop="state">
                <a-radio-group v-model="lawyer.state">
                  <a-radio v-for="judge in judgeCode" :value="judge.codeCode">{{judge.codeName}}</a-radio>
                </a-radio-group>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="证件类型" prop="certType">

                <a-select v-model="lawyer.certType">
                  a<a-select-option value="">请选择证件类型</a-select-option>
                  <a-select-option v-for="certType in certTypeCode" :value="certType.codeCode">{{certType.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="证件号码" prop="certNo">
                <a-input v-model="lawyer.certNo">
                </a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="最高学历" prop="edu">
                <a-select v-model="lawyer.edu">
                  <a-select-option value="">请选择最高学历</a-select-option>
                  <a-select-option v-for="edu in eduCode" :value="edu.codeCode">{{edu.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="最高学位" prop="degree">
                <a-select v-model="lawyer.degree">
                  <a-select-option value="">请选择最高学位</a-select-option>
                  <a-select-option v-for="degree in degreeCode" :value="degree.codeCode">{{degree.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="所内身份">
                <a-select v-model="lawyer.identity">
                  <a-select-option value="">请选择所内身份</a-select-option>
                  <a-select-option v-for="identity in identityCode" :value="identity.codeCode">{{identity.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="民族">
                <a-select v-model="lawyer.nation">
                  <a-select-option value="">请选择民族</a-select-option>
                  <a-select-option v-for="nation in nationCode" :value="nation.codeCode">{{nation.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="政治面貌">
                <a-select v-model="lawyer.politic">
                  <a-select-option value="">请选择政治面貌</a-select-option>
                  <a-select-option v-for="politic in politicsStatusCode" :value="politic.codeCode">{{politic.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="婚姻状况">
                <a-select v-model="lawyer.marial">
                  <a-select-option value="">请选择婚姻状况</a-select-option>
                  <a-select-option v-for="marial in marialCode" :value="marial.codeCode">{{marial.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="门禁号">
                <a-input v-model="lawyer.doorNo"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              从业资料
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="执业类别" prop="jobType">
                <a-select v-model="lawyer.jobType">
                  <a-select-option value="">请选择执业类别</a-select-option>
                  <a-select-option v-for="jobType in jobTypeCode" :value="jobType.codeCode">{{jobType.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="执业证号码" prop="jobNo">
                <a-input v-model="lawyer.jobNo"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="首次执业时间">
                <a-date-picker valueFormat="YYYY-MM-DD" style="width: 100%" v-model="lawyer.firstJobTime" placeholder="首次执业时间"></a-date-picker>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="首次执业地">
                <a-input v-model="lawyer.firstJobAddress"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="资格证类别 ">
                <a-select v-model="lawyer.statusType">
                  <a-select-option value="">请选择资格证类别</a-select-option>
                  <a-select-option v-for="statusType in statusTypeCode" :value="statusType.codeCode">{{statusType.codeName}}</a-select-option>
                </a-select>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="资格证号码">
                <a-input v-model="lawyer.statusNo"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="资格证获取方式">
                <a-input v-model="lawyer.gainType"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="资格证获取地址">
                <a-input v-model="lawyer.gainAddress"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="擅长领域" prop="adept">
                <a-input v-model="lawyer.adept"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-form-model-item label="个人介绍" :label-col="{span:2}">
                <a-textarea :row="4" v-model="lawyer.introduction"></a-textarea>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              入职情况
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="入职时间" prop="entryTime">
                <a-date-picker  valueFormat="YYYY-MM-DD" style="width: 100%" v-model="lawyer.entryTime" placeholder="请选择入职时间"></a-date-picker>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="社保号码">
                <a-input  style="width: 100%" v-model="lawyer.socialNo"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="合同开始时间" prop="contractStartTime">
                <a-date-picker valueFormat="YYYY-MM-DD" style="width: 100%" v-model="lawyer.contractStartTime"></a-date-picker>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="合同结束时间" prop="contractEndTime">
                <a-date-picker valueFormat="YYYY-MM-DD" style="width: 100%" v-model="lawyer.contractEndTime"></a-date-picker>
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-form-model-item label="参保情况" :label-col="{span:2}" :wrapper-col="{span:22}">
                <a-checkbox-group
                  v-model="lawyer.insurance"
                  :options="insureCode"
                />
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              联系信息
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="12">
              <a-form-model-item label="手机号码" prop="tel">
                <a-input  v-model="lawyer.tel"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="照片">
                <a-upload
                  name="file"
                  :multiple="true"
                  action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                >
                  <a-button> <a-icon type="upload" />照片上传</a-button>
                </a-upload>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="联系地址" prop="telAddress">
                <a-input  style="width: 100%" v-model="lawyer.telAddress"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="联系电话">
                <a-input  style="width: 100%" v-model="lawyer.telNo"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="邮箱">
                <a-input  style="width: 100%" v-model="lawyer.email"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="邮政编码">
                <a-input  style="width: 100%" v-model="lawyer.emailNo"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="qq号码">
                <a-input  style="width: 100%" v-model="lawyer.qq"></a-input>
              </a-form-model-item>
            </a-col>
            <a-col :span="12">
              <a-form-model-item label="微信号">
                <a-input  style="width: 100%" v-model="lawyer.weChat"></a-input>
              </a-form-model-item>
            </a-col>
          </a-row>

          <a-row :gutter="24">
          <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
            教育经历 <a-button @click="eduAdd">添加</a-button>
          </a-col>
        </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-table bordered :data-source="lawyer.eduList" :columns="eduColumns">
                <template slot="dateStart" slot-scope="text, record">
                  <a-row :gutter="24">
                    <a-col :span="12">
                      <a-date-picker valueFormat="YYYY-MM-DD" v-model="record.startTime" placeholder="起始"></a-date-picker>
                    </a-col>
                    <a-col :span="12">
                      <a-date-picker valueFormat="YYYY-MM-DD" v-model="record.endTime" placeholder="终止"></a-date-picker>
                    </a-col>
                  </a-row>
                </template>
                <template slot="school" slot-scope="text, record">
                  <a-input v-model="record.school"/>
                </template>
                <template slot="pro" slot-scope="text, record">
                  <a-input v-model="record.pro"/>
                </template>
                <template slot="education" slot-scope="text, record">
                  <a-input v-model="record.education"/>
                </template>
                <template slot="degree" slot-scope="text, record">
                  <a-input v-model="record.degree"/>
                </template>
                <template slot="operation" slot-scope="text, record">
                  <a-button type="link" @click="deleteEdu(record)">删除</a-button>
                </template>
              </a-table>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              工作经历 <a-button @click="workAdd">添加</a-button>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-table bordered :data-source="lawyer.workList" :columns="workColumns">
                <template slot="dateStart" slot-scope="text, record">
                  <a-row :gutter="24">
                    <a-col :span="12">
                      <a-date-picker valueFormat="YYYY-MM-DD" v-model="record.startTime" placeholder="起始"></a-date-picker>
                    </a-col>
                    <a-col :span="12">
                      <a-date-picker valueFormat="YYYY-MM-DD" v-model="record.endTime" placeholder="终止"></a-date-picker>
                    </a-col>
                  </a-row>
                </template>
                <template slot="work" slot-scope="text, record">
                  <a-input v-model="record.work"/>
                </template>
                <template slot="duty" slot-scope="text, record">
                  <a-input v-model="record.duty"/>
                </template>
                <template slot="workType" slot-scope="text, record">
                  <a-input v-model="record.workType"/>
                </template>
                <template slot="nature" slot-scope="text, record">
                  <a-input v-model="record.nature"/>
                </template>
                <template slot="operation" slot-scope="text, record">
                  <a-button type="link" @click="deleteWork(record)">删除</a-button>
                </template>
              </a-table>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              获奖记录 <a-button @click="rewardAdd">添加</a-button>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-table bordered :data-source="lawyer.rewordList" :columns="rewordColumns">
                <template slot="time" slot-scope="text, record">
                  <a-date-picker v-model="record.time" valueFormat="YYYY-MM-DD" placeholder="获奖时间"/>
                </template>
                <template slot="rank" slot-scope="text, record">
                  <a-input v-model="record.rank"/>
                </template>
                <template slot="authority" slot-scope="text, record">
                  <a-input v-model="record.authority"/>
                </template>
                <template slot="content" slot-scope="text, record">
                  <a-input v-model="record.content"/>
                </template>
                <template slot="operation" slot-scope="text, record">
                  <a-button type="link" @click="deleteReward(record)">删除</a-button>
                </template>
              </a-table>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24" style="margin-bottom: 30px; font-size:20px; font-family: 黑体; font-weight: bolder">
              惩罚记录 <a-button @click="punishAdd">添加</a-button>
            </a-col>
          </a-row>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-table bordered :data-source="lawyer.punishList" :columns="punishColumns">
                <template slot="time" slot-scope="text, record">
                  <a-date-picker v-model="record.time" valueFormat="YYYY-MM-DD" placeholder="惩罚时间"/>
                </template>
                <template slot="content" slot-scope="text, record">
                  <a-input v-model="record.content"/>
                </template>
                <template slot="rank" slot-scope="text, record">
                  <a-input v-model="record.rank"/>
                </template>
                <template slot="organ" slot-scope="text, record">
                  <a-input v-model="record.organ"/>
                </template>
                <template slot="reason" slot-scope="text, record">
                  <a-input v-model="record.reason"/>
                </template>
                <template slot="operation" slot-scope="text, record">
                  <a-button type="link" @click="deletePunish(record)">删除</a-button>
                </template>
              </a-table>
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
    import req from '@/req';
    import editableCell from "../../components/editableCell";
    import editableTimeCell from "../../components/editableTimeCell";
    const eduColumns = [{
        title: '起止时间',
        dataIndex: 'operation',
        width: '30%',
        scopedSlots: { customRender: 'dateStart' }
    },{
        title: '所在学校',
        dataIndex: 'school',
        scopedSlots: { customRender: 'school' }
    },{
        title: '主修专业',
        dataIndex: 'pro',
        scopedSlots: { customRender: 'pro' }
    },{
        title: '获得学历',
        dataIndex: 'education',
        scopedSlots: { customRender: 'education' }
    },{
        title: '获得学位',
        dataIndex: 'degree',
        scopedSlots: { customRender: 'degree' }
    },{
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' }
    }];

    const workColumns = [{
        title: '起止时间',
        width: '30%',
        scopedSlots: { customRender: 'dateStart' }
    },{
        title: '工作单位',
        dataIndex: 'work',
        scopedSlots: { customRender: 'work' }
    },{
        title: '工作职位',
        dataIndex: 'duty',
        scopedSlots: { customRender: 'duty' }
    },{
        title: '工作类别',
        dataIndex: 'workType',
        scopedSlots: { customRender: 'workType' }
    },{
        title: '单位性质',
        dataIndex: 'nature',
        scopedSlots: { customRender: 'nature' }
    },{
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' }
    }];

    const rewordColumns = [{
        title: '获奖时间',
        dataIndex: 'time',
        width:'20%',
        scopedSlots: { customRender: 'time' }
    },{
        title: '获奖级别',
        dataIndex: 'rank',
        scopedSlots: { customRender: 'rank' }
    },{
        title: '颁发机构',
        dataIndex: 'authority',
        scopedSlots: { customRender: 'authority' }
    },{
        title: '获奖内容',
        dataIndex: 'content',
        scopedSlots: { customRender: 'content' }
    },{
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' }
    }];
    const punishColumns = [{
        title: '惩罚日期',
        dataIndex: 'time',
        scopedSlots: { customRender: 'time' }
    },{
        title: '惩罚事项',
        dataIndex: 'content',
        scopedSlots: { customRender: 'content' }
    },{
        title: '惩罚级别',
        dataIndex: 'rank',
        scopedSlots: { customRender: 'rank' }
    },{
        title: '惩处机构',
        dataIndex: 'organ',
        scopedSlots: { customRender: 'organ' }
    },{
        title: '惩处事由',
        dataIndex: 'reason',
        scopedSlots: { customRender: 'reason' }
    },{
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' }
    }];
    export default {
        name: "v-address",
        components: {
            tableNav,
            editableCell,
            editableTimeCell
        },
        created(){

        },
        mounted(){
            let scope = this;
            req.GET("code/getCodesByType", {codeType: 'insure'}, function (response) {
                let data = response.data.data;
                data.forEach(function (value) {
                    scope.$data.insureCode.push({ label: value.codeName, value: value.codeCode})
                })
            });

            req.GET("code/getCodesByType", {codeType: 'judge'}, function (response) {
                scope.$data.judgeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'sex'}, function (response) {
                scope.$data.sexCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'certType'}, function (response) {
                scope.$data.certTypeCode = response.data.data;
            });

            req.GET("code/getCodesByType", {codeType: 'edu'}, function (response) {
                scope.$data.eduCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'degree'}, function (response) {
                scope.$data.degreeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'nation'}, function (response) {
                scope.$data.nationCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'politicsStatus'}, function (response) {
                scope.$data.politicsStatusCode = response.data.data;
            });

            req.GET("code/getCodesByType", {codeType: 'marial'}, function (response) {
                scope.$data.marialCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'jobType'}, function (response) {
                scope.$data.jobTypeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'statusType'}, function (response) {
                scope.$data.statusTypeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'identity'}, function (response) {
                scope.$data.identityCode = response.data.data;
            });

        },
        data() {
            return {
                eduColumns,
                workColumns,
                rewordColumns,
                punishColumns,
                lawyer:{
                    name : null,
                    sex : null,
                    birthday : null,
                    state : null,
                    certType : null,
                    certNo : null,
                    edu : null,
                    degree : null,
                    identity : null,
                    nation : null,
                    politic : null,
                    marial : null,
                    doorNo : null,
                    jobType : null,
                    jobNo : null,
                    firstJobTime : null,
                    firstJobAddress:null,
                    statusType: null,
                    statusNo: null,
                    gainType: null,
                    gainAddress: null,
                    adept: null,
                    introduction: null,
                    entryTime: null,
                    socialNo: null,
                    contractStartTime: null,
                    contractEndTime: null,
                    insurance: null,
                    tel: null,
                    picture: null,
                    telAddress: null,
                    telNo: null,
                    email: null,
                    emailNo: null,
                    qq: null,
                    weChat: null,
                    eduList:[{startTime: null}],
                    workList:[{}],
                    rewordList:[{}],
                    punishList:[{}]
                },
                rules:{
                    name:[{required:true, message:'姓名不能为空', trigger: 'change'}],
                    sex:[{required:true, message:'性别不能为空', trigger: 'change'}],
                    birthday:[{required:true, message:'出生日期不能为空', trigger: 'change'}],
                    state:[{required:true, message:'是否在职不能为空', trigger: 'change'}],
                    certType:[{required:true, message:'证件类型不能为空', trigger: 'change'}],
                    certNo:[{required:true, message:'证件号码不能为空', trigger: 'change'}],
                    edu:[{required:true, message:'最高学历不能为空', trigger: 'change'}],
                    degree:[{required:true, message:'最高学位不能为空', trigger: 'change'}],
                    jobType:[{required:true, message:'执业类别不能为空', trigger: 'change'}],
                    jobNo:[{required:true, message:'执业号码不能为空', trigger: 'change'}],
                    adept:[{required:true, message:'擅长领域不能为空', trigger: 'change'}],
                    entryTime:[{required:true, message:'入职时间不能为空', trigger: 'change'}],
                    contractStartTime:[{required:true, message:'合同开始时间不能为空', trigger: 'change'}],
                    contractEndTime:[{required:true, message:'合同结束时间不能为空', trigger: 'change'}],
                    tel:[{required:true, message:'手机号码不能为空', trigger: 'change'}],
                    telAddress:[{required:true, message:'联系地址不能为空', trigger: 'change'}]
                },
                insureCode:[],
                judgeCode:[],
                sexCode:[],
                certTypeCode:[],
                eduCode:[],
                degreeCode:[],
                nationCode:[],
                politicsStatusCode:[],
                marialCode:[],
                jobTypeCode:[],
                statusTypeCode:[],
                identityCode:[]
            };
        },
        methods: {
            eduAdd(){
                this.$data.lawyer.eduList.push({startTime:null});
            },
            workAdd(){
                this.$data.lawyer.workList.push({});
            },
            rewardAdd(){
                this.$data.lawyer.rewordList.push({});
            },
            punishAdd(){
                this.$data.lawyer.punishList.push({});
            },
            deleteEdu(record){
                let dataList = this.$data.lawyer.eduList;
                dataList.splice(dataList.indexOf(record), 1);
            },
            deleteWork(record){
                let dataList = this.$data.lawyer.workList;
                dataList.splice(dataList.indexOf(record), 1);
            },
            deleteReward(record){
                let dataList = this.$data.lawyer.rewordList;
                dataList.splice(dataList.indexOf(record), 1);
            },
            deletePunish(record){
                let dataList = this.$data.lawyer.punishList;
                dataList.splice(dataList.indexOf(record), 1);
            },
            submit(){
                let scope = this;
                this.$refs['lawyerRef'].validate(valid =>{
                    if(valid){
                        req.POST("lawyer/insert", this.$data.lawyer, function (response) {
                            scope.$router.push({name:'User'});
                        });
                    }
                })
            }
        }
    };
</script>
<style scoped>
  .editable-row-operations a {
    margin-right: 8px;
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
  .editable-cell {
    position: relative;
  }

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 100%;
    cursor: pointer;
  }

  .editable-cell-icon {
    line-height: 18px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell:hover .editable-cell-icon {
    display: inline-block;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
</style>
