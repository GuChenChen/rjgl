package com.fykj.scaffold.evaluation.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WorkBenchOneVo {
    /**
     * 测试数
     */
    private int num;
    /**
     * 月份
     */
    private String viewMonth;
    /**
     * 实收金额
     */
    private BigDecimal received;
    /**
     * 应收金额
     */
    private BigDecimal receivable;

}
