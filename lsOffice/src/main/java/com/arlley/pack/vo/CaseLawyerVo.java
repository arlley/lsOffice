package com.arlley.pack.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CaseLawyerVo implements Serializable {

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
