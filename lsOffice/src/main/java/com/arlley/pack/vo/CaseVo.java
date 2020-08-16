package com.arlley.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CaseVo implements Serializable {

    private int id;

    private String caseNo;

    private LocalDate time;

    private String customName;

    private String party;

    private String lawyer;

    private String caseState;

    private BigDecimal proxyAmount;

    private BigDecimal alreadyPay;

    private BigDecimal alreadyInvoice;

    private String approveState;

    private String approver;

}
