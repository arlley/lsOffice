package com.arlley.pack.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Lawer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 是否在职
     */
    private String state;

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
     * 最高学历
     */
    private String edu;

    /**
     * 学位
     */
    private String degree;

    /**
     * 所内身份
     */
    private String identity;

    /**
     * 民族
     */
    private String nation;

    /**
     * 政治面貌
     */
    private String politic;

    /**
     * 婚姻状况
     */
    private String marial;

    /**
     * 门禁号
     */
    @TableField("doorNo")
    private String doorNo;

    /**
     * 执业类别
     */
    @TableField("jobType")
    private String jobType;

    /**
     * 执业号码
     */
    @TableField("jobNo")
    private String jobNo;

    /**
     * 首次执业时间
     */
    @TableField("firstJobTime")
    private LocalDate firstJobTime;

    /**
     * 首次执业地址
     */
    @TableField("firstJobAddress")
    private String firstJobAddress;

    /**
     * 资格证类别
     */
    @TableField("statusType")
    private String statusType;

    /**
     * 资格证号码
     */
    @TableField("statusNo")
    private String statusNo;

    /**
     * 资格证获取方式
     */
    @TableField("gainType")
    private String gainType;

    /**
     * 资格证获取地址
     */
    @TableField("gainAddress")
    private String gainAddress;

    /**
     * 擅长领域
     */
    private String adept;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 入职时间
     */
    @TableField("entryTime")
    private LocalDate entryTime;

    /**
     * 社保号码
     */
    @TableField("socialNo")
    private String socialNo;

    /**
     * 合同开始时间
     */
    @TableField("contractStartTime")
    private LocalDate contractStartTime;

    /**
     * 合同结束时间
     */
    @TableField("contractEndTime")
    private LocalDate contractEndTime;

    /**
     * 参保情况
     */
    private String insurance;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 照片
     */
    private String picture;

    /**
     * 联系地址
     */
    @TableField("telAddress")
    private String telAddress;

    /**
     * 联系电话
     */
    @TableField("telNo")
    private String telNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮政编码
     */
    @TableField("emailNo")
    private String emailNo;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 微信号
     */
    @TableField("weChat")
    private String weChat;


}
