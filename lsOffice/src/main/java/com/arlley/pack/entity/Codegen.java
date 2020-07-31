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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Codegen implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开头字母
     */
    private String pNo;

    /**
     * 数字长度
     */
    private Integer numLength;

    /**
     * 数字部分最大值
     */
    private Integer maxNum;


}
