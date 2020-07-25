package com.arlley.pack.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author wanggang
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QianchengUserTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */

    private long id;
    private String userName;

    /**
     * 用户密码md5加密
     */
    private String password;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 1:男 2:女
     */
    private Integer sex;

    /**
     * 0:不在职 1:在职
     */
    private Integer onTheJob;

    /**
     * 1:身份证
     */
    private Integer certificateType;

    /**
     * 证件编码
     */
    private String certificateNum;

    /**
     * 最高学历 1:博士 2:硕士 3:本科 4:专科 5:高中 6:初中 7:小学
     */
    private Integer highestEducation;

    /**
     * 所内身份
     */
    private Integer lawFirmIdentity;

    /**
     * 民族 1:汉
     */
    private Integer nation;

    /**
     * 1:党员 2:民主人士 3:共青团员 4:群众
     */
    private Integer politicCountenance;

    /**
     * 0:未婚 1:已婚 2:离异
     */
    private Integer maritalStatus;

    /**
     * 门禁号
     */
    private String accessControlNumber;

    /**
     * 专业部
     */
    private Integer professionalDepartment;


}
