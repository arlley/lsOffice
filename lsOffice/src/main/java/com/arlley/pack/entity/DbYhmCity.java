package com.arlley.pack.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DbYhmCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    private Integer classParentId;

    private String className;

    private Boolean classType;


}
