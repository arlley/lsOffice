package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EduVo implements Serializable {

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
