package com.arlley.pack.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class LawyerVo implements Serializable {
    /**
     * 主键
     */
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
    private String certType;

    /**
     * 证件号码
     */
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
    private String doorNo;

    /**
     * 执业类别
     */
    private String jobType;

    /**
     * 执业号码
     */
    private String jobNo;

    /**
     * 首次执业时间
     */
    private LocalDate firstJobTime;

    /**
     * 首次执业地址
     */
    private String firstJobAddress;

    /**
     * 资格证类别
     */
    private String statusType;

    /**
     * 资格证号码
     */
    private String statusNo;

    /**
     * 资格证获取方式
     */
    private String gainType;

    /**
     * 资格证获取地址
     */
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
    private LocalDate entryTime;

    /**
     * 社保号码
     */
    private String socialNo;

    /**
     * 合同开始时间
     */
    private LocalDate contractStartTime;

    /**
     * 合同结束时间
     */
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
    private String telAddress;

    /**
     * 联系电话
     */
    private String telNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮政编码
     */
    private String emailNo;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 微信号
     */
    private String weChat;


    /**
     * 学习经历
     */
    private List<EduVo> eduList;

    private List<WorkVo> workList;

    private List<RewardVo> rewordList;

    private List<PunishVo> punishList;
}
