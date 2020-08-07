package com.arlley.pack.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
public class Work implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 起始时间
     */
    @TableField("startTime")
    private LocalDate startTime;

    /**
     * 结束时间
     */
    @TableField("endTime")
    private LocalDate endTime;

    /**
     * 工作单位
     */
    private String work;

    /**
     * 工作职位
     */
    private String duty;

    /**
     * 工作职位
     */
    @TableField("workType")
    private String workType;

    /**
     * 工作性质
     */
    private String nature;

    /**
     * 外键
     */
    private Integer lid;


}
