package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PunishVo implements Serializable {

    private Integer id;

    /**
     * 获奖日期
     */
    private LocalDate time;

    /**
     * 惩罚事项
     */
    private String content;

    /**
     * 惩处级别
     */
    private String rank;

    /**
     * 惩处机构
     */
    private String organ;

    /**
     * 惩处事由
     */
    private String reason;

    /**
     * 外键
     */
    private Integer lid;
}
