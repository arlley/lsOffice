package com.arlley.pack.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayDetailDetailVo implements Serializable {

    private Integer id;
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


}
