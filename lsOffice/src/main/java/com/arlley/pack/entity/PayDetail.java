package com.arlley.pack.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 是否是主账户
     */
    private Integer isSpecial;

    /**
     * 收入类别
     */
    private String payType;

    /**
     * 其他类别补充
     */
    private String payTypeDetail;

    /**
     * 收支日期
     */
    private LocalDate payTime;

    /**
     * 收支金额
     */
    private BigDecimal payAmount;

    /**
     * 支付方式
     */
    private String payWay;

    /**
     * 录入日期
     */
    private LocalDate createTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 关联案件id
     */
    private Integer caseId;

    /**
     * 收入或者支出1 支出 0 收入
     */
    @TableField("incomeType")
    private Integer incomeType;

    @TableField("lawyer_id")
    private Integer lawyerId;
}
