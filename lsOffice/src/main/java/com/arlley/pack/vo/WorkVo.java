package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class WorkVo implements Serializable {

    private Integer id;

    /**
     * 起始时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
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
