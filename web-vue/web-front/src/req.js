import Vue from 'vue';
import axios from 'axios';
import {notification} from 'ant-design-vue';

const getLocal = function () {
    return localStorage.getItem("token");
}

const POST = function (url, paramObject, callback) {
    if(paramObject == null){
        axios.post(url).then(function (response) {
            if(callback){
                callback(response);
            }
        }).catch(function (error) {
            console.error(error);
        });
    }else{
        axios.post(url, JSON.stringify(paramObject)).then(function (response) {
            if(callback){
                callback(response);
            }
        }).catch(function (error) {
            console.error(error);
        });
    }
}

const GET = function (url, paramObject, callback) {
    if(paramObject == null){
        axios.get(url).then(function (response) {
            if(callback){
                callback(response);
            }
        });
    }else{
        axios.get(url, {params: paramObject}).then(function (response) {
            if(callback){
                callback(response);
            }
        });
    }
}


axios.interceptors.response.use(function (response) {
    if(response.data.code == 200){
      return response;
    }

    notification.error({
      message: '错误信息',
      duration: 2,
      description:response.data.message
    });
    throw response;
});
axios.interceptors.request.use(function (config) {
  config.headers.Authorization = getLocal();
  return config;
});


export default {POST, GET}
