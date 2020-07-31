<style  scoped>
    .my-select{
        max-width: 200px;
        appearance:none;
        border:0;
        background-color: transparent
    }
</style>

<template>
    <select class="form-control my-select" v-bind:value="value"  v-on:change="$emit('input', $event.target.value)">
        <option v-for="syscode in syscodes" :value="syscode.codeCode">{{syscode.codeName}}</option>
    </select>
</template>

<script>
    import req from "../req";
    export default {
        name:'pay-type-select-code',
        props:['value', 'type'],
        data(){
            return{
                syscodes:[{
                    codeCode:'',
                    codeName:''
                }],
                state:''
            }
        },
        created() {
            var scope = this;
            req.GET("code/getCodesByType", {codeType: this.type}).then(function (response) {
                scope.$data.syscodes = response.data;
            });
        },
        methods:{

        }
    }
</script>