package com.fykj.scaffold.evaluation.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecordAttachment;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApplyNoteTestRecordVo {

    private String id;

    /**
     * 申请单主键
     */
    private String applyNoteId;

    /**
     * 启动时间
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime endDate;

    /**
     * 应收金额
     */
    private BigDecimal amountReceivable;

    /**
     * 实收金额
     */
    private BigDecimal amountReceived;

    /**
     * 是否付款
     */
    private Boolean payment;

    /**
     * 到款日期
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)  //格式化前台日期参数注解
    private LocalDateTime paymentDate;

    /**
     * 发票号码
     */
    private String invoiceNumber;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流单号
     */
    private String logisticsNote;

    /**
     * 本测试是否完成
     */
    private Boolean finished;

    /**
     * 附件（单附件）
     */
    private String fileUrl;

    /**
     * 备注
     */
    private String remark;

    /**
    * 文档列表
    */
    private List<ApplyNoteTestRecordAttachment> attachmentList;

    private String createDate;

    private Integer version;

    private Integer deleted;

    private String updateDate;

    private String creator;

    private String updater;

}
