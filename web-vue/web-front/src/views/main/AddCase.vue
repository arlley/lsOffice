<template>
  <div class="w-100">
    <tableNav localName="新增案件" showFirstBtn="true" firstBtnName="返回" :firstCallBack="putOff"></tableNav>
    <div class="pt-4" style="width:800px">
      <a-steps v-model="current" type="navigation" size="small" :style="stepStyle">

        <a-step status="finish" title="选择案件类型">
          <a-icon slot="icon" type="idcard" />
        </a-step>
        <a-step status="finish" title="利益冲突检查">
          <a-icon slot="icon" type="idcard" />
        </a-step>
        <a-step status="process" title="新增委托人">
          <a-icon slot="icon" type="idcard" />
        </a-step>
        <a-step status="wait" title="登记立案信息"></a-step>
        <a-step status="wait" title="立案文书上传"></a-step>
        <a-step status="wait" title="收案审批"></a-step>
      </a-steps>
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
        },
        data() {
            return {
                current: 0,
                stepStyle: {
                    marginBottom: '60px',
                    boxShadow: '0px -1px 0 0 #e8e8e8 inset',
                    marginLeft: '10px'
                },
            };
        },
        methods: {
            putOff() {
                this.$router.push({name:'Pay'}, null);
            }
        }
    };
</script>
<style scoped>

</style>
