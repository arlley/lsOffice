<template>
  <div class="w-100">
    <tableNav localName="新增案件" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="pt-4" style="width:100%; margin-left: 10px">
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

        </a-tab-pane>
        <a-tab-pane key="4" :disabled="tab4">
          <span slot="tab">
            <a-divider type="vertical" />
            立案登记信息
            <a-icon type="right" />
          </span>
          Content of Tab Pane 2
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
                tab2:true,
                tab3:false,
                tab4:true,
                tab5:true,
                tab6:true,
                expand: false,
                form: this.$form.createForm(this, { name: 'advanced_search' }),
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
                }]
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

  #components-form-demo-advanced-search .ant-form {
    max-width: none;
  }
  #components-form-demo-advanced-search .search-result-list {
    margin-top: 16px;
    border: 1px dashed #e9e9e9;
    border-radius: 6px;
    background-color: #fafafa;
    min-height: 200px;
    text-align: center;
    padding-top: 80px;
  }
</style>
