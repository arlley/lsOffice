package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RewardVo implements Serializable {
    private Integer id;

    /**
     * 获奖时间
     */
    private LocalDate time;

    /**
     * 获奖级别
     */
    private String rank;

    /**
     * 颁发机构
     */
    private String authority;

    /**
     * 获奖内容
     */
    private String content;
}
