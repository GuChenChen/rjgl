package com.fykj.scaffold.evaluation.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.annotation.DictTrans;
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
public class ApplyNoteTestRecordAttachmentVo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 测试记录主键id
	 */
	private String recordId;

	/**
	 * 文档类型
	 */
	@DictTrans(transTo = "typeText")
	private String type;

	private String typeText;

	/**
	 * 附件地址
	 */
	private String url;

	/**
	 * 原始文件名
	 */
	private String originalName;

}
