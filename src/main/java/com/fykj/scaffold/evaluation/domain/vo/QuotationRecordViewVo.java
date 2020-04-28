package com.fykj.scaffold.evaluation.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotationRecordViewVo {
    /**
     * 月份
     */
    private String viewMonth;
    /**
     * 甲方
     */
    private String  partyA;
    /**
     * 乙方B
     */
    private String partyB;
    /**
     * 功能测试
     */
    private Integer functionalTest;
    /**
     * 性能测试
     */
    private Integer performanceTest;
    /**
     * 安全测试
     */
    private Integer securityTest;
}
