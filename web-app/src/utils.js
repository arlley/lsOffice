/* jshint esversion: 6 */
/**
 * 提取字符首字符大写，小写，和剩余字符串
 * @param {String} local 
 */
import Vue from 'vue';
const getName = unknow => {
    let local='';
    if (typeof(unknow)=='string') {
        local = unknow;
    } else if (unknow instanceof Vue) {
        local = unknow.$options.name;
    }
    return local;
};
const splitName = obj => {
    let local = getName(obj);
    let num = local.indexOf("-");
    while (num != -1) {
        local = local.slice(num + 1);
        num = local.indexOf("-");
    }
    let pre = local.slice(0, 1);
    let other = local.slice(1);
    let uPre = pre.toLocaleUpperCase();
    let dPre = pre.toLocaleLowerCase();
    return {
        uPre: uPre,//大写首字母
        dPre: dPre,//小学首字母
        other: other//除首字母外其他字符
    };
};
/**
 * 获取vuex初始化数据函数名
 * @param {String} local 
 */
const getInitName = local => {
    let strs = splitName(local);
    return "init".concat(strs.uPre, strs.other);
};
/**
 * 获取vuex选中数据函数名
 * @param {String} local 
 */
const getSelectName = local => {
    let strs = splitName(local);
    return "select".concat(strs.uPre, strs.other);
};
/**
 * 获取当前组件的跳转链接
 * @param {String} local 
 */
const getPageLink = local => {
    let strs = splitName(local);
    return "/main/".concat(strs.dPre, strs.other);
};
/**
 * 获取当前组件的增添页面的跳转链接
 * @param {String} local 
 */
const getAddPageLink = local => {
    let strs = splitName(local);
    return "/main/add-".concat(strs.dPre, strs.other);
};
/**
 * 获取当前组件的删除页面的跳转链接
 * @param {String} local 
 */
const getModPageLink = local => {
    let strs = splitName(local);
    return "/main/mod-".concat(strs.dPre, strs.other);
};
/**
 * 获取操作成功的跳转链接
 * @param {String} local 
 */
const getSuccessLink = () => {
    return "/main/success";
};
/**
 * 获取操作失败的跳转链接
 * @param {String} local 
 */
const getFailureLink = () => {
    return "/main/failure";
};
const getLocalArrayName = (local) => {
    let str = getName(local);
    return str + "List";
};
const getLength = array => {
    let item;
    for (item in array);
    return item;
};
const utils = {
    getSuccessLink, getFailureLink, getInitName, getSelectName,
    getPageLink, getAddPageLink, getModPageLink, getLocalArrayName,
    getLength
};
export default utils;