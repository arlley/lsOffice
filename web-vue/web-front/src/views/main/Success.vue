<template>
  <div>
    <tableNav localName="Success" showFirstBtn="true" firstBtnName="返回" :firstCallBack="clinkBack"></tableNav>
    <div class="mx-auto d-flex justify-content-center align-items-center" style="height:800px;width:400px">
      <div class="w-75 h-25 d-flex justify-content-center" style="width:300;">
        <span class="fa fa-check text-success" style="font-size:100px;"></span>
        <span style="font-size:20px;line-height:100px;width:200px;">{{message}}</span>
      </div>
    </div>
  </div>
</template>
<script>
    import tableNav from "../../components/TableNav";
    export default {
        name: "success",
        components: {
            tableNav
        },
        data() {
            return {
                message: this.$route.query.message,
                local: ""
            };
        },
        methods: {
            clinkBack() {
                this.local = this.$route.query.local;
                this.$router.push({name : this.local});
            }
        }
    };
</script>
<style scoped>
</style>
