package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LawyerSearchVo implements Serializable {

    private String name;

    private String identity;

    private Date startDate;

    private Date endDate;
}
