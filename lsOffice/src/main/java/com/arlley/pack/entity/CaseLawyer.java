package com.arlley.pack.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaseLawyer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 律师
     */
    private Integer lawyer;

    /**
     * 角色
     */
    private String job;

    /**
     * 业绩分成
     */
    private BigDecimal amount;

    /**
     * 分成方式
     */
    @TableField("amountType")
    private String amountType;

    /**
     * 案件号
     */
    private Integer caseId;


}
