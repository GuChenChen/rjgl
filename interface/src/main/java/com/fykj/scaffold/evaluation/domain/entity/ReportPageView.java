package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>
 *  模块使用记录表
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReportPageView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 查看时间
     */
    @TableField("view_time")
    @ApiModelProperty(value = "查看时间")
    private LocalDate viewTime;

    /**
     * 模块（字典）
     */
    @TableField("page_code")
    @ApiModelProperty(value = "模块（字典）")
    private String pageCode;

    /**
     * 模块文本
     */
    @TableField("page_text")
    @ApiModelProperty(value = "模块文本")
    private String pageText;

    /**
     * 单位主键
     */
    @TableField("evaluation_user_id")
    @ApiModelProperty(value = "单位主键")
    private String evaluationUserId;

    /**
     * 用户IP
     */
    @TableField("ip")
    @ApiModelProperty(value = "用户IP")
    private String ip;

    /**
     * 内容
     */
    @TableField("content")
    @ApiModelProperty(value = "内容")
    private String content;

}
