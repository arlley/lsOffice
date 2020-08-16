<template>
  <div class="w-100">
    <tableNav localName="我的客户"></tableNav>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-page-header
          title="客户管理/我的客户"
          @back="$router.go(-1)"
        />
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="24">
        <div class="components-form-demo-advanced-search">
          <a-form class="ant-advanced-search-form" :label-col="{span : 8}" layout="inline"
                  :wrapper-col="{span: 16}">
            <a-row :gutter="24">
              <a-col :span="24">
                <a-form-item>
                  <a-input  v-model="searchData.text">
                  </a-input>
                </a-form-item>
                <a-form-item style="min-width: 300px;margin-left:-85px">
                  <a-select v-model="searchData.tough">
                    <a-select-option value="0">客户（委托人）搜索</a-select-option>
                    <a-select-option value="1">电话搜索</a-select-option>
                  </a-select>
                </a-form-item>
                <a-form-item label="是否指派" style="min-width: 200px">
                  <a-select v-model="searchData.assign">
                    <a-select-option value="">请选择</a-select-option>
                    <a-select-option v-for="judge in judgeCode"  :value="judge.codeCode">{{judge.codeName}}</a-select-option>
                  </a-select>、
                </a-form-item>
                <a-form-item label="客户类型">
                  <a-select v-model="searchData.type" style="min-width: 200px">
                    <a-select-option value="">请选择</a-select-option>
                    <a-select-option v-for="customType in customTypeCode" :value="customType.codeCode">{{customType.codeName}}</a-select-option>
                  </a-select>、
                </a-form-item>
              </a-col>
            </a-row>
            <a-row>
              <a-col :span="24" :style="{ textAlign: 'right' }">
                <a-button type="primary" html-type="submit" @click="search()">
                  检索
                </a-button>
                <a-button type="default" html-type="submit" @click="add()">
                  添加客户
                </a-button>
                <a-button :style="{ marginLeft: '8px' }">
                  添加服务记录
                </a-button>
                <a-button :style="{ marginLeft: '8px' }">
                  导出
                </a-button>
              </a-col>
            </a-row>
          </a-form>
          <div class="search-result-list">
            <a-table :columns="columns" :data-source="dataSource">
              <span slot="action" slot-scope="text, record">
                <a v-on:click="alert(record.id)">修改</a>
                <a-divider type="vertical" />
                <a v-on:click="deleteRecord(record.id)">删除</a>
                <a-divider type="vertical" />
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
    import req from "../../req";
    export default {
        name: "add-address",
        components: {
            tableNav
        },
        data() {
            return {
                columns:[{
                    title:'客户（委托人）',
                    dataIndex:'name'
                },{
                    title:'联系方式',
                    dataIndex:'tel'
                },{
                    title:'地区',
                    dataIndex:'region'
                },{
                    title:'是否指派',
                    dataIndex:'assign'
                },{
                    title:'入库时间',
                    dataIndex:'createTime'
                },{
                    title: '操作',
                    dataIndex: 'operation',
                    scopedSlots: { customRender: 'action' }
                }],
                dataSource:[],
                searchData:{
                    name:'',
                    type:'',
                    tel:'',
                    assign:'',
                    tough:'0',
                    text:''
                },
                judgeCode:[],
                customTypeCode:[]

            };
        },
        mounted(){
            let scope = this;
            req.GET("custom/list", null, function (response) {
                scope.$data.dataSource = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'judge'}, function (response) {
                scope.$data.judgeCode = response.data.data;
            });
            req.GET("code/getCodesByType", {codeType: 'customType'}, function (response) {
                scope.$data.customTypeCode = response.data.data;
            });
        },
        methods: {
            search(){
                let scope = this;
                let data = this.$data.searchData;
                if(data.tough ==="0"){
                    data.name = data.text;
                    data.tel = null;
                }else{
                    data.tel = data.text;
                    data.name = null;
                }
                req.POST("custom/search", data, function (response) {
                    scope.$data.dataSource = response.data.data;
                });
            },
            add(){
                this.$router.push({name: 'AddCustom'}, null);
            },
            alert(id){
                this.$router.push({name:'AlertCustom', query:{id: id}}, null);
            },
            deleteRecord(id){
                let scope = this;
                req.GET("custom/delete", {id:id}, function (res) {
                    req.POST("custom/search", scope.$data.searchData, function (response) {
                        scope.$data.dataSource = response.data.data;
                    });
                });

            }
        }
    };
</script>
<style scoped>
  .components-form-demo-advanced-search .ant-form {
    max-width: none;
    padding: 10px;
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
</style>
