<template>
  <div>
    <tableNav
      localName="我的案件"
    ></tableNav>
    <a-page-header
      title="我的案件"
      @back="$router.go(-1)"
    />
    <a-row :gutter="24">
      <a-col :span="24">
        <div class="components-form-demo-advanced-search">
          <a-form class="ant-advanced-search-form" :label-col="{span:8}" :wrapper-col="{span:16}">
            <a-row :gutter="24">
              <a-col :span="6">
                <a-form-item label="委托人/当事人">
                  <a-input v-model="searchData.customName"  placeholder="委托人/当事人"/>
                </a-form-item>
              </a-col>

              <a-col :span="6">
                <a-form-item label="对方当事人">
                  <a-input v-model="searchData.party" placeholder="对方当事人"/>
                </a-form-item>
              </a-col>
            </a-row>
            <a-row>
              <a-col :span="24" :style="{ textAlign: 'right' }">
                <a-button type="primary" @click="search">
                  Search
                </a-button>
              </a-col>
            </a-row>
          </a-form>
          <div class="search-result-list">
            <a-table :columns="columns" :data-source="data">
              <span slot="operation" slot-scope="text, record">
                <a-button type="link" @click="caseDetail(record)">详情</a-button>
                <a-divider type="vertical" />
                <a-button type="link" @click="deleteUser(record.id)">删除</a-button>
              </span>
            </a-table>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script>
    import tableNav from "../../components/TableNav";
    import req from '../../req';
    const columns = [
        {
            title: '案号',
            dataIndex: 'caseNo',
            width: '7%',
            scopedSlots: { customRender: 'type' }
        },
        {
            title: '收案日期',
            dataIndex: 'time',
            width: '7%',

        },
        {
            title: '委托人',
            dataIndex: 'customName',
            width: '7%'
        },
        {
            title: '对方当事人',
            dataIndex: 'party',
            width: '7%'
        },
        {
            title: '承办律师',
            dataIndex: 'lawyer',
            width: '10%'
        },
        {
            title: '结案',
            dataIndex: 'caseState',
            width: '10%'
        },
        {
            title: '代理费（元）',
            dataIndex: 'proxyAmount',
            width: '10%'
        },
        {
            title: '已付款（元）',
            dataIndex: 'alreadyPay',
            width: '10%'
        },
        {
            title: '已开票（元）',
            dataIndex: 'alreadyInvoice',
            width: '10%'
        },
        {
            title: '收案审批',
            dataIndex: 'approveState',
            width: '10%'
        },
        {
            title: '收案审批人',
            dataIndex: 'approver',
            width: '10%'
        },
        {
            title: '操作',
            dataIndex: 'operation',
            width: '10%',
            scopedSlots: { customRender: 'operation' }
        }
    ];
    const data = [];
    export default {
        components: {
            tableNav
        },
        created(){

        },
        mounted(){
            let scope = this.$data;
            let t = this;
            req.GET("code/getCodesByType", {codeType: 'income'}, function (response) {
                let result = response.data.data;
                result.forEach(function (value, index) {
                    scope.syscodes[value.codeCode] = value.codeName;
                })
                req.GET("code/getCodesByType", {codeType: 'payType'}, function (response) {
                    let result = response.data.data;
                    result.forEach(function (value, index) {
                        scope.PaySyscodes[value.codeCode] = value.codeName;
                    })
                    t.flush();
                });
            });


        },
        data() {
            this.cacheData = data.map(item => ({ ...item }));
            return {
                data,
                columns,
                syscodes:{},
                PaySyscodes:{},
                searchData:{}
            };
        },
        methods: {
            flush:function () {
                let scope = this.$data;
                req.POST("case/main/search", {}, function (response) {
                    scope.data = response.data.data;
                    scope.data.forEach(function (value) {
                        value.typeName = scope.syscodes[value.type];
                        value.PayName = scope.PaySyscodes[value.payType]
                    })
                })
            },
            caseDetail:function (record) {
                this.$router.push({name:'CaseDetail',
                    query:{
                        id:record.id
                    }});
            },
            deleteUser:function (id) {
                let scope = this;
                req.GET('pay/delete', {id:id},function (response) {
                    scope.flush()
                })
            },
            search:function () {
                let scope = this.$data;
                req.POST("case/main/search", this.$data.searchData, function (response) {
                    scope.data = response.data.data;
                    scope.data.forEach(function (value) {
                        value.typeName = scope.syscodes[value.type];
                        value.PayName = scope.PaySyscodes[value.payType]
                    })
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
    padding:30px
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
