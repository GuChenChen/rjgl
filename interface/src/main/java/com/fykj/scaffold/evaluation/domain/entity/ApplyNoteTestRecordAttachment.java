package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * 
 * @author LiMingyang
 * @email ${email}
 * @date 2020-04-09 15:11:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("apply_note_test_record_attachment")
public class ApplyNoteTestRecordAttachment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 测试记录主键id
	 */
	@TableField("record_id")
	@ApiModelProperty(value = "测试记录主键id")
	private String recordId;

	/**
	 * 文档类型
	 */
	@TableField("type")
    @ApiModelProperty(value = "文档类型")
	private String type;

	/**
	 * 附件地址
	 */
	@TableField("url")
    @ApiModelProperty(value = "附件地址")
	private String url;

	/**
	 * 原始文件名
	 */
	@TableField("original_name")
	@ApiModelProperty(value = "原始文件名")
	private String originalName;

}
