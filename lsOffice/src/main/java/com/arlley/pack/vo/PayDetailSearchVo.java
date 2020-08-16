package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PayDetailSearchVo implements Serializable {

    private String payType;

    private String payWay;

    private LocalDate startTime;

    private LocalDate endTime;

    private int id;
}
