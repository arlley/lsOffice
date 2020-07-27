<template>
    <select  class="form-control">
        <option v-for="syscode in syscodes" :value="syscode.codeCode">{{syscode.codeName}}</option>
    </select>
</template>
<script>
    import req from "../req";
    export default {
        name:'select-code',
        props:{
            codeType:''
        },
        data(){
            return{
                syscodes:[{
                    codeCode:'',
                    codeName:''
                }],
                model:''
            }
        },
        created() {
            var scope = this;
            req.GET("code/getCodesByType", {codeType: this.codeType}).then(function (response) {
                scope.$data.syscodes = response.date;
            });
        }
    }
</script>