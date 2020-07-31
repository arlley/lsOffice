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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Case implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收案日期
     */
    private LocalDate time;

    /**
     * 委托人
     */
    private String bailor;

    /**
     * 对方当事人
     */
    private String party;

    /**
     * 承办律师
     */
    private Integer uid;

    /**
     * 结案状态
     */
    private String state;

    /**
     * 代理费
     */
    private BigDecimal proxyAmount;

    /**
     * 已付款
     */
    private BigDecimal alreadyAmount;

    /**
     * 已开票
     */
    private BigDecimal alreadyPayInvoiceAmount;

    /**
     * 收案审批状态
     */
    private String approveState;

    /**
     * 收案审批人
     */
    private Integer approveUid;

    /**
     * 合同编号
     */
    @TableField("contract_No")
    private String contractNo;

    /**
     * 档案编号
     */
    @TableField("record_No")
    private String recordNo;


}
