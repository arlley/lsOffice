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
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Punish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 获奖日期
     */
    @TableField(value = "punish_time")
    private LocalDate time;

    /**
     * 惩罚事项
     */

    private String content;

    /**
     * 惩处级别
     */
    @TableField(value = "punish_rank")
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
