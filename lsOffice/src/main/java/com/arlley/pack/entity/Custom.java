package com.arlley.pack.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Custom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户名字
     */
    private String name;

    /**
     * 客户电话
     */
    private String tel;

    /**
     * 客户类型
     */
    private String type;

    /**
     * 以逗号分割
     */
    private String trait;

    /**
     * 证件类型
     */
    @TableField("certType")
    private String certType;

    /**
     * 证件号码
     */
    @TableField("certNo")
    private String certNo;

    /**
     * 民族
     */
    private String nation;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 国家和地区
     */
    private String nature;

    /**
     * 文化程度
     */
    private String edu;

    /**
     * 省
     */
    private String pro;

    /**
     * 市
     */
    private String city;

    /**
     * 户籍地
     */
    private String domicile;

    /**
     * 详细地址/通讯地址
     */
    private String address;

    /**
     * 主要负责人
     */
    private String principal;

    /**
     * 业务联系人
     */
    private String businesser;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 职务
     */
    private String duty;

    /**
     * 地区影响力
     */
    private String influence;

    /**
     * 固定电话
     */
    private String fixTel;

    /**
     * 客户状态
     */
    private String state;

    /**
     * 法定代表人
     */
    private String lawer;

    /**
     * 法定代表人联系方式
     */
    private String lawerTel;

    /**
     * 以逗号分割的文件路径
     */
    private String filesPath;

    /**
     * 备注
     */
    private String note;

    /**
     * 是否指派
     */
    private String assign;


    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;


}
