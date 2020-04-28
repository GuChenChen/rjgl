package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 通知
 * 
 * @author wangming
 * @email ${email}
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("notice")
public class Notice extends BaseEntity  {

	private static final long serialVersionUID = 1L;

	/**
	 * 节点代码
	 */
	@TableField("code")
    @ApiModelProperty(value = "节点代码")
	@NotBlank(message = "节点编码不能为空")
	@Size(max = 20, message = "节点编码不能超过20个字符")
	private String code;

	/**
	 * 节点名称
	 */
	@TableField("name")
    @ApiModelProperty(value = "节点名称")
	@NotBlank(message = "节点名称不能为空")
	@Size(max = 50, message = "节点名称不能超过50个字符")
	private String name;

	/**
	 * 发送方式
	 */
	@TableField("type")
    @ApiModelProperty(value = "发送方式")
	@NotBlank(message = "请选择发送方式")
	private String type;

	/**
	 * 发送方式
	 */
	@TableField(exist = false)
    @ApiModelProperty(value = "发送方式")
	private String typeName;

	/**
	 * 接收对象(角色id)
	 */
	@TableField("role_id")
    @ApiModelProperty(value = "接收对象")
	@NotBlank(message = "请选择接收对象")
	private String roleId;

	/**
	 * 接收对象
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "接收对象")
	private String roleName;

	/**
	 * 通知内容
	 */
	@TableField("notice_content")
    @ApiModelProperty(value = "通知内容")
	@NotBlank(message = "通知内容不能为空")
	@Size(max = 200, message = "通知内容不能超过200个字符")
	private String noticeContent;

	/**
	 * 备注
	 */
	@TableField("remark")
    @ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 云片网key
	 */
	@TableField("api_key")
    @ApiModelProperty(value = "云片网key")
	@Size(max = 100, message = "云片网key不能超过100个字符")
	private String apiKey;


}
