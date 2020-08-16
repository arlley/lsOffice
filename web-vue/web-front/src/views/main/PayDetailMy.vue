<template>
  <div>
    <tableNav
      localName="个人收支统计"
    ></tableNav>
    <div class="components-form-demo-advanced-search" style="padding-top: 70px">
      <a-form-model ref="ruleForm"
                    :model="searchData"
                    :rules="rules" layout="inline">
        <a-form-model-item>
          <a-select v-model="searchData.payType" style="min-width: 200px" :defaultValue="null">
            <a-select-option :value="null">请选择收支类别</a-select-option>
            <a-select-option v-for="(value,key) in payTypeCode" :value="key">{{value}}</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item>
          <a-select v-model="searchData.payWay" style="min-width: 200px">
            <a-select-option :value="null">请选择收款方式</a-select-option>
            <a-select-option v-for="(value,key) in payWayCode" :value="key">{{value}}</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="收支时间" prop="searchTime">
          <a-range-picker valueFormat="YYYY-MM-DD" v-model="searchData.searchTime">
          </a-range-picker>
        </a-form-model-item>
        <a-form-model-item>
          <a-button type="primary" @click="search">检索</a-button>
          <a-button style="margin-left: 10px" @click="addIncome">添加收入</a-button>
          <a-button style="margin-left: 10px" @click="addOutcome">支出</a-button>
        </a-form-model-item>
      </a-form-model>

      <div class="search-result-list">
        <a-table :columns="columns" :data-source="data">
          <template slot="incomeType" slot-scope="text, record">
            <span v-if="record.incomeType == 1">收入</span>
            <span v-if="record.incomeType == 0">支出</span>
          </template>
        </a-table>
      </div>

      <div class="search-result-list" style="text-align: left">
        <span >个人收支统计 收入{{payDetailVo.income}} 收入分类:{{payDetailVo.incomeStatistics}} 支出{{payDetailVo.outCome}} 支出分类{{payDetailVo.outComeStatistics}}</span>
      </div>
    </div>
  </div>
</template>
<script>
    import tableNav from "../../components/TableNav";
    import req from '../../req';
    const columns = [{
        title: '收支类别',
        dataIndex: 'payType',
        width: '10%'
    },{
        title: '收支日期',
        dataIndex: 'payTime',
        width: '10%',

    },{
        title: '收支金额',
        dataIndex: 'payAmount',
        width: '10%'
    },{
        title: '支付方式',
        dataIndex: 'payWay',
        width: '10%'
    },{
        title: '备注',
        dataIndex: 'note',
        width: '10%'
    },{
        title: '收入/支出',
        dataIndex: 'incomeType',
        width: '10%',
        scopedSlots: { customRender: 'incomeType' }
    }];
    const data = [];
    export default {
        components: {
            tableNav
        },
        created(){

        },
        mounted(){
            let scope = this.$data;
            let id = localStorage.getItem("id");
            req.GET("code/getCodesByType", {codeType: 'payWay'}, function (response) {
                let result = response.data.data;
                scope.payTypeCode = {};
                result.forEach(function (value, index) {
                    scope.payTypeCode[value.codeCode] = value.codeName;
                })
            });
            req.GET("code/getCodesByType", {codeType: 'payType'}, function (response) {
                let result = response.data.data;
                scope.payWayCode = {}
                result.forEach(function (value, index) {
                    scope.payWayCode[value.codeCode] = value.codeName;
                })
            });
            req.GET("/pay/detail/person", {id, id}, function (response) {
                scope.payDetailVo = response.data.data;
            })
        },
        data() {
            this.cacheData = data.map(item => ({ ...item }));
            return {
                data,
                columns,
                searchData:{payType:null, payWay:null, searchTime:[]},
                rules:{searchTime:[{required:true, message:'请选择时间', trigger: 'change'}]},
                payTypeCode:{},
                payWayCode:{},
                payDetailVo:{
                    income:null,
                    outCome:null,
                    incomeStatistics:null,
                    outComeStatistics:null
                }
            };
        },
        methods: {
            search(){
                let scope = this;
                let id = localStorage.getItem("id");
                this.$refs['ruleForm'].validate(valid =>{
                    if(valid){
                        scope.$data.searchData.startTime = scope.$data.searchData.searchTime[0];
                        scope.$data.searchData.endTime = scope.$data.searchData.searchTime[1];
                        scope.$data.searchData.id = id;
                        req.POST("/pay/detail/my", scope.$data.searchData, function (response) {
                            scope.$data.data=response.data.data;
                            scope.$data.data.forEach(function (value) {
                                value.payType = scope.$data.payTypeCode[value.payType];
                                value.payWay = scope.$data.payWayCode[value.payWay];
                            });
                        });
                    }
                })

            },
            addIncome(){
                this.$router.push({name:'AddLawyerMyPayIn'})
            },
            addOutcome(){
                this.$router.push({name:'AddLawyerMyPayOut'})
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
    padding:30px
  }
  .components-form-demo-advanced-search .search-result-list {
    position: relative;
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
