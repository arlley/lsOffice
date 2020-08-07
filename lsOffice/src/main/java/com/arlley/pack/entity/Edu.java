package com.arlley.pack.entity;

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
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Edu implements Serializable {

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
     * 所在学校
     */
    private String school;

    /**
     * 主修专业
     */
    private String pro;

    /**
     * 获得学历
     */
    private String education;

    /**
     * 获得学位
     */
    private String degree;

    /**
     * 律师
     */
    private Integer lid;


}
