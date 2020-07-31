<template>
    <select v-bind:value="value"  class="form-control" v-on:change="$emit('input', $event.target.value)">
        <option v-for="syscode in syscodes" :value="syscode.codeCode">
            <span v-if="syscode.codeCode.length == 1">
                {{syscode.codeName}}
            </span>
            <span v-if="syscode.codeCode.length == 2">
                ------{{syscode.codeName}}
            </span>
        </option>
    </select>
</template>
<script>
    import req from "../req";
    export default {
        name:'select-code',
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
        mounted() {
            var scope = this;
            req.GET("code/getCodesByType", {codeType: this.type}).then(function (response) {
                scope.$data.syscodes = response.data;
            });
        },
        methods:{
        }
    }
</script>