package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PayDetailVo implements Serializable {

    private String name;

    // 收入
    private BigDecimal income;

    // 支出
    private BigDecimal outCome;

    // 收入统计
    private String incomeStatistics;

    // 支出统计
    private String outComeStatistics;
}
