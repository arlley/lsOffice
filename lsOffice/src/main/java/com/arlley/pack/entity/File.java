package com.arlley.pack.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanggang
 * @since 2020-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer uid;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件状态
     */
    private String status;

    /**
     * 文件路径
     */
    private String url;


}
