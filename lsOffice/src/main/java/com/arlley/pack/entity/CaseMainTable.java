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
 * @since 2020-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaseMainTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 案号
     */
    @TableField("caseNo")
    private String caseNo;

    /**
     * 案由
     */
    private String reason;

    /**
     * 性质
     */
    private String nature;

    /**
     * 是否涉外
     */
    private String foreigner;

    /**
     * 合同号
     */
    @TableField("contractNo")
    private String contractNo;

    /**
     * 委托人
     */
    private Integer customId;

    /**
     * 对方当事人
     */
    private String party;

    /**
     * 对方当事人地位
     */
    private String partyStatus;

    /**
     * 我方当事人
     */
    private String partyMy;

    /**
     * 收案日期
     */
    private LocalDate time;

    /**
     * 受理部门
     */
    private String department;

    /**
     * 诉讼标的
     */
    private String lawsuit;

    /**
     * 代理费
     */
    private BigDecimal proxyAmount;

    /**
     * 计费方式
     */
    private String chargeMode;

    /**
     * 是否享受政府补助
     */
    private String gov;

    /**
     * 补助金额
     */
    private BigDecimal govAmount;

    /**
     * 诉讼阶段
     */
    private String lawSuitPro;

    /**
     * 诉讼地位
     */
    private String lawSuitStatus;

    /**
     * 专属案号
     */
    private String excluCaseNo;

    /**
     * 案源人
     */
    private String sourcer;

    /**
     * 第三人
     */
    private String thirdParty;

    /**
     * 杂费
     */
    private BigDecimal incidental;

    /**
     * 是否指派
     */
    private String assign;

    /**
     * 办案省
     */
    private String pro;

    /**
     * 办案市
     */
    private String city;

    /**
     * 办案县
     */
    private String county;

    /**
     * 相关文件
     */
    private String file;

    /**
     * 备注
     */
    private String note;

    @TableField("caseState")
    private String caseState;
    @TableField("approveState")
    private String approveState;
    @TableField("approver")
    private String approver;
    @TableField("alreadyPay")
    private BigDecimal alreadyPay;
}
