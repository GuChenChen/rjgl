package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  测试申请单附件
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApplyNoteAttachment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 申请单主键
     */
    @TableField("apply_note_id")
    @ApiModelProperty(value = "申请单主键")
    private String applyNoteId;

    /**
     * 附件类型
     */
    @TableField("type")
    @ApiModelProperty(value = "附件类型")
    private String type;

    /**
     * 附件地址
     */
    @TableField("url")
    @ApiModelProperty(value = "附件地址")
    private String url;

    /**
     * 备注
     */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 原文件名
     */
    @TableField("file_name")
    @ApiModelProperty(value = "原文件名")
    private String fileName;
}
