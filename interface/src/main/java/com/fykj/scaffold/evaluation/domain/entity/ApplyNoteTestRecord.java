package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *  测试申请单测试记录表
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApplyNoteTestRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 申请单主键
     */
    @TableField("apply_note_id")
    @ApiModelProperty(value = "申请单主键")
    private String applyNoteId;

    /**
     * 启动时间
     */
    @TableField("start_date")
    @ApiModelProperty(value = "启动时间")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    @TableField("end_date")
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime endDate;

    /**
     * 应收金额
     */
    @TableField("amount_receivable")
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amountReceivable;

    /**
     * 实收金额
     */
    @TableField("amount_received")
    @ApiModelProperty(value = "实收金额")
    private BigDecimal amountReceived;

    /**
     * 是否付款
     */
    @TableField("is_payment")
    @ApiModelProperty(value = "是否付款")
    private Boolean payment;

    /**
     * 到款日期
     */
    @TableField("payment_date")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)  //格式化前台日期参数注解
    @ApiModelProperty(value = "到款日期")
    private LocalDateTime paymentDate;

    /**
     * 发票号码
     */
    @TableField("invoice_number")
    @ApiModelProperty(value = "发票号码")
    private String invoiceNumber;

    /**
     * 物流公司
     */
    @TableField("logistics_company")
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;

    /**
     * 物流单号
     */
    @TableField("logistics_note")
    @ApiModelProperty(value = "物流单号")
    private String logisticsNote;

    /**
     * 本测试是否完成
     */
    @TableField("is_finished")
    @ApiModelProperty(value = "本测试是否完成")
    private Boolean finished;

    /**
     * 附件（单附件）
     */
    @TableField("file_url")
    @ApiModelProperty(value = "附件（单附件）")
    private String fileUrl;

    /**
     * 备注
     */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;


}
