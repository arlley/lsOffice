package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CaseVo implements Serializable {

    private int id;

    private String caseNo;

    private LocalDate time;

    private String customName;

    private String party;

    private String lawyer;



}
