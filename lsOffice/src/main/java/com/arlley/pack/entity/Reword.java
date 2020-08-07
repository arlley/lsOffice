package com.arlley.pack.entity;

import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Reword implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 获奖时间
     */
    @TableField(value = "reward_time")
    private LocalDate time;

    /**
     * 获奖级别
     */
    @TableField(value = "reward_rank")
    private String rank;

    /**
     * 颁发机构
     */
    private String authority;

    /**
     * 获奖内容
     */
    private String content;


    private int lid;
}
