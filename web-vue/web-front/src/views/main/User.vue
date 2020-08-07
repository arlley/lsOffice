<template>
  <div>
    <tableNav
      localName="人事管理"
    ></tableNav>
    <a-table :columns="columns" :data-source="data" bordered style="padding-top: 100px">
      <span slot="state" slot-scope="text, record">
        <span v-if="record.state == 1">在职</span>
        <span v-if="record.state == 0">离职</span>
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
            title: '姓名',
            dataIndex: 'name',
            width: '25%'
        },
        {
            title: '电话',
            dataIndex: 'tel',
            width: '15%'
        },
        {
            title: '状态',
            dataIndex: 'state',
            width: '40%',
            scopedSlots: { customRender: 'state' }
        },
        {
            title: '操作',
            dataIndex: 'operation',
            scopedSlots: { customRender: 'operation' }
        },
    ];
    const data = [{
        key:'1',
        id: '1',
        name: '王刚',
        tel: '18796011811',
        state: '在职'
    }];
    export default {
        name: "v-address",
        components: {
            tableNav
        },
        mounted(){
            this.flush();
        },
        data() {
            this.cacheData = data.map(item => ({ ...item }));
            return {
                data,
                columns
            };
        },
        methods: {
            flush:function () {
                let scope = this.$data;
                req.POST("user/address", null, function (response) {
                    scope.data = response.data.data;
                })
            },
            alert:function (record) {
                this.$router.push({name:'AlertUser',
                                  query:{
                                    id:record.id
                }});
            },
            deleteUser:function (id) {
                let scope = this;
                req.GET('user/delete', {id:id},function (response) {
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
