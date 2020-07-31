<template>
    <span>{{codeName}}</span>
</template>

<script>
    import req from "../req";
    export default {
        name:'code-show',
        props:['value', 'code', 'type'],
        data(){
            return{
                codeName:'',
                codeCode:''
            }
        },
        mounted() {
            var scope = this;
            req.GET("code/getCodeByCodeType", {codeType: this.type, codeCode: this.code}).then(function (response) {
                scope.$data.codeName = response.data.codeName;
                scope.$data.codeCode = response.data.codeCode;
            });
        },
        methods:{

        }
    }
</script>