package com.fykj.scaffold.evaluation.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuotationWardVo {
    /**
     * 上浮金额
     */
    private BigDecimal upWard;

    /**
     * 下浮金额
     */
    private BigDecimal downWard;
}
