package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 测试申请单实体
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApplyNote extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 申请单位主键
     */
    @TableField("evaluation_user_id")
    @ApiModelProperty(value = "申请单位主键")
    private String evaluationUserId;

    /**
     * 申请单位名称
     */
    @TableField("evaluation_user_name")
    @ApiModelProperty(value = "申请单位名称")
    private String evaluationUserName;

    /**
     * 申请单号
     */
    @TableField("code")
    @ApiModelProperty(value = "申请单号")
    private String code;

    /**
     * 测试项目名称
     */
    @TableField("project_name")
    @ApiModelProperty(value = "测试项目名称")
    private String projectName;

    /**
     * 版本
     */
    @TableField("versions")
    @ApiModelProperty(value = "版本")
    private String versions;

    /**
     * 测试类型
     */
    @TableField("type")
    @ApiModelProperty(value = "测试类型")
    private String type;

    /**
     * 状态code
     */
    @TableField("status")
    @ApiModelProperty(value = "状态code")
    private String status;

    /**
     * 测试系统概述
     */
    @TableField("system_summarize")
    @ApiModelProperty(value = "测试系统概述")
    private String systemSummarize;

    /**
     * 申请单位手机号
     */
    @TableField("tel")
    @ApiModelProperty(value = "申请单位手机号")
    private String tel;

    /**
     * 申请单位手机号
     */
    @TableField("test_report_no")
    @ApiModelProperty(value = "测试报告编号(测试报告名)")
    private String testReportNo;

    /**
     * 申请单位手机号
     */
    @TableField("test_report_url")
    @ApiModelProperty(value = "测试报告文件地址")
    private String testReportUrl;

    /**
     * 结束时间
     */
    @TableField(value = "end_date", updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "申请单的结束时间（测试通过时间）")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime endDate;
}
