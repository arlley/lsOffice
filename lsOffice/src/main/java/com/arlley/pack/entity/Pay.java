package com.arlley.pack.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Pay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收支类别
     */
    private String type;

    /**
     * 收支日期
     */
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate time;

    /**
     * 收支金额
     */
    private BigDecimal amount;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 案号
     */
    private String caseNo;

    /**
     * 录入日期
     */
    @JsonFormat(pattern = "yyyy-mm-dd'T'hh:MM")
    private LocalDateTime createTime;

}
