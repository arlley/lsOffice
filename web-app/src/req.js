/* jshint esversion: 6*/
let baseURL = "http://localhost:26001/";
import $axios from 'axios';
import {notification} from 'ant-design-vue';
const concatURL = url => {
    return  url;
};


const POST = function (url, paramObject) {
    if (paramObject == null) {
        return $axios.post(concatURL(url)).then(res => {
            return res.data;
        }).catch(function (e) {

        });
    }
    return $axios.post(concatURL(url), JSON.stringify(paramObject)).then(res => {
        return res.data;
    }).catch(function (e) {
        
    });
};
const GET = function (url, paramObject) {
    if (paramObject == null) {
        return $axios.get(concatURL(url)).then(res => {
            return res.data;
        }).catch(function (e) {

        });
    }
    return $axios.get(concatURL(url), {params: paramObject}).then(res => {
        return res.data;
    }).catch(function (e) {

    });
};

const my =  $axios.interceptors.response.use(function (response) {
    if(response.data.code == 200){
        return response;
    }
    notification.error({
        message: '错误信息',
        duration: 2,
        description:response.data.message
    });
    if(response.data.code == 400){

    }
    throw "error";
});
export default {
    POST, GET
};