package com.fykj.scaffold.evaluation.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuotationRecordVo {
    /**
     * 操作人/IP
     */
    private String creator;
    /**
     * 软件部分投入金额
     */
    private BigDecimal inputPrise;
    /**
     * 子系统个数
     */
    private Integer subsystemCount;
    /**
     * 子系统模块数
     */
    private Integer subsystemModuleCount;
    /**
     * 模块功能点数
     */
    private Integer moduleFunctionCount;
    /**
     * 并发数
     */
    private Integer concurrentCount;
    /**
     * 性能数
     */
    private Integer performanceCount;
    /**
     * 代码行数
     */
    private Integer codeLinesCount;
    /**
     * 系统数
     */
    private Integer systemCount;
    /**
     * 报价金额
     */
    private BigDecimal quotation;
    /**
     * 报价时间
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime createDate;

    /**
     * 报价浮动金额
     */
    private String ward;
}
