<template>
  <div>
    <tableNav
      localName="收支管理"
    ></tableNav>
    <a-table :columns="columns" :data-source="data" bordered>
      <span slot="operation" slot-scope="text, record">
        {{}}
      </span>
      <span slot="operation" slot-scope="text, record">
        <a-button type="link" @click="alert(record)">修改</a-button>
        <a-divider type="vertical" />
        <a-button type="link" @click="deleteUser(record.id)">删除</a-button>
      </span>
    </a-table>
  </div>
</template>
<script>
    import tableNav from "../../components/TableNav";
    import req from '@/req';
    const columns = [
        {
            title: '收支类别',
            dataIndex: 'typeName',
            width: '40%',
            scopedSlots: { customRender: 'type' }
        },
        {
            title: '收支日期',
            dataIndex: 'time',
            width: '10%',

        },
        {
            title: '收支金额',
            dataIndex: 'amount',
            width: '10%'
        },
        {
            title: '支付方式',
            dataIndex: 'PayName',
            width: '10%'
        },
        {
            title: '案号',
            dataIndex: 'caseNo',
            width: '10%'
        },
        {
            title: '操作',
            dataIndex: 'operation',
            scopedSlots: { customRender: 'operation' }
        },
    ];
    const data = [];
    export default {
        name: "v-address",
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
                PaySyscodes:{}
            };
        },
        methods: {
            flush:function () {
                let scope = this.$data;
                req.GET("pay/pays", null, function (response) {
                    scope.data = response.data.data;
                    scope.data.forEach(function (value) {
                        value.typeName = scope.syscodes[value.type];
                        value.PayName = scope.PaySyscodes[value.payType]
                    })
                })
            },
            alert:function (record) {
                this.$router.push({name:'AlertPay',
                    query:{
                        id:record.id
                    }});
            },
            deleteUser:function (id) {
                let scope = this;
                req.GET('pay/delete', {id:id},function (response) {
                    scope.flush()
                })
            }
        }
    };
</script>
<style scoped>
  .editable-row-operations a {
    margin-right: 8px;
  }
</style>
