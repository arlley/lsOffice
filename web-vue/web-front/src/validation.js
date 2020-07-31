const formatTel = function (rule, value, callback) {
    if(!(/^1[3456789]\d{9}$/.test(value))){
        callback(new Error("用户名格式不正确"));
    }
    callback();
}

const formatNum = function (rule, value, callback) {
  if(!(/^[0-9]+(.[0-9]{2})?$/.test(value))){
    callback(new Error("金额格式不正确"));
  }
    callback();
}


export default {
  formatTel, formatNum
}
