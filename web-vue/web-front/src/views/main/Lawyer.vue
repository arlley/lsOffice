<template>
  <div>
    <tableNav
      localName="人事管理"
    ></tableNav>
    <a-page-header
      title="人事/人事管理"
      @back="$router.go(-1)"
    />

    <div class="components-form-demo-advanced-search">
      <a-form class="ant-advanced-search-form" layout="inline">
        <a-row :gutter="24">
          <a-col :span="24">
            <a-form-item>
              <a-input v-model="searchData.name" placeholder="姓名/手机号码"/>
            </a-form-item>
            <a-form-item>
              <a-select v-model="searchData.identity" style="min-width: 100px">
                <a-select-option value="">选择身份</a-select-option>
                <a-select-option v-for="identity in identityCode" :value="identity.codeCode">{{identity.codeName}}</a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item>
              <a-range-picker valueFormat="YYYY-MM-DD" v-model="searchData.time" />
            </a-form-item>
            <a-button type="primary" @click="search">检索</a-button>
          </a-col>
        </a-row>
      </a-form>
      <div class="search-result-list">
        <a-table :columns="columns" :data-source="result">
          <span slot="identity" slot-scope="text, record">
            <span v-for="identity in identityCode" v-if="record.identity == identity.codeCode">{{identity.codeName}}</span>
          </span>
          <span slot="state" slot-scope="text, record">
            <span v-for="judge in judgeCode" v-if="record.state == judge.codeCode">{{judge.codeName}}</span>
          </span>
          <span slot="action" slot-scope="text, record">
            <a-button type="link">删除</a-button>
            <a-divider type="vertical" />
          </span>
        </a-table>
      </div>
    </div>
  </div>
</template>
<script>
    import tableNav from "../../components/TableNav";
    import req from '@/req';
    const columns = [{
        title:'身份',
        dataIndex:'identity',
        scopedSlots: { customRender: 'identity' }
    },{
        title:'姓名',
        dataIndex:'name'
    },{
        title:'手机号码',
        dataIndex:'tel'
    },{
        title:'入职时间',
        dataIndex:'entryTime'
    },{
        title:'合同到期',
        dataIndex:'contractEndTime'
    },{
        title:'是否在职',
        dataIndex:'state',
        scopedSlots: { customRender: 'state' }
    },{
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'action' }
    }]
    export default {
        name: "v-address",
        components: {
            tableNav
        },
        created(){

        },
        mounted(){
            let scope = this;
            req.GET("code/getCodesByType", {codeType: 'judge'}, function (response) {
                scope.$data.judgeCode = response.data.data;
                req.GET("code/getCodesByType", {codeType: 'identity'}, function (response) {
                    scope.$data.identityCode = response.data.data;
                    req.POST("lawyer/query", {}, function (response) {
                        scope.$data.result = response.data.data;
                    });
                });
            });

        },
        data() {
            return {
                columns,
                searchData: {
                    name: null,
                    identity: null,
                    time:[]
                },
                result:[],
                identityCode:[],
                judgeCode:[]
            }
        },
        methods: {
            search(){
                let searchData = this.$data.searchData;
                searchData.startDate = searchData.time[0];
                searchData.endDate = searchData.time[1];
                let scope = this;
                req.POST("lawyer/query", searchData, function (response) {
                    scope.$data.result = response.data.data;
                });
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
    padding: 10px;
  }
  .components-form-demo-advanced-search .search-result-list {
    margin-top: 26px;
    margin-left: 10px;
    border: 1px dashed #e9e9e9;
    border-radius: 6px;
    background-color: #fafafa;
    min-height: 200px;
    text-align: center;
  }
</style>
