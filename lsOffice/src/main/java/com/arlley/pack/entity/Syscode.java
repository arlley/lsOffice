package com.arlley.pack.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Syscode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 代码
     */
    private String codeCode;

    /**
     * 代码名称
     */
    private String codeName;

    /**
     * 代码类型
     */
    private String codeType;

    /**
     * 父代码类型
     */
    private String parentType;

    /**
     * 父代码
     */
    private String parentCode;


}
