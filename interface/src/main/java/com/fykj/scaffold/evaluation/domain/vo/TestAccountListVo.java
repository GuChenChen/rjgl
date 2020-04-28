package com.fykj.scaffold.evaluation.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.annotation.DictTrans;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TestAccountListVo {
    private String id;
    private String companyName;
    private String mobile;
    private String organizationCodeUrl;
    private String companyAddr;
    private String name;
    private String socialCreditCode;
    private boolean status;
    private String statusName;
    private String email;
    private String invoiceTitle;
    private String taxNumber;
    private String accountOpenBank;
    private String accountOpenAccount;
    private String writeInvoiceAddress;
    private String writeInvoicePhone;
    private String remark;

    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime createDate;
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime lastLoginDate;
    private String createDateText;
    private String lastLoginDateText;
    private Integer userVersion;
    private Integer evaluationVersion;
}
