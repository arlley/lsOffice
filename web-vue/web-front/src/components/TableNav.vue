<template>
  <div style="max-height:70px;background:#4D5E70; position:fixed; width:100%;z-index:100">
    <div class="d-flex justify-content-between h-100" style="line-height:70px;">
      <span class="pl-3 font-weight-bold" style="font-size:20px;">
        <span class="text-white">当前位置&nbsp;&gt;&gt;</span>
        <span class="text-warning">{{localName}}</span>
      </span>
      <span class="pr-5 justify-content-around">
        <span v-show="showFirstBtn">
          <button class="btn btn-secondary m-1" @click="firstClick">{{firstBtnName}}</button>
        </span>
        <span v-show="showSecondBtn">
          <button class="btn btn-secondary m-1" @click="secondClick">{{secondBtnName}}</button>
        </span>
      </span>
    </div>
  </div>
</template>
<script>
    export default {
        name: "v-table-nav",
        props: {
            localName: "",
            showFirstBtn:false,
            firstBtnName:"",
            firstCallBack:Function,
            showSecondBtn:false,
            secondBtnName: "",
            secondCallBack:Function
        },
        methods: {
            firstClick() {
                this.firstCallBack();
            },
            secondClick() {
                this.secondCallBack();
            }
        }
    };
</script>
<style scoped>
</style>
