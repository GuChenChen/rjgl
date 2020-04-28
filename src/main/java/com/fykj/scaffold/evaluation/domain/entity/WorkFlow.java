package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 流程
 *
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("work_flow")
public class WorkFlow extends BaseEntity  {

	private static final long serialVersionUID = 307073551510366414L;

	@TableField("flow_name")
    @ApiModelProperty(value = "流程名称")
	@NotBlank(message = "流程名称不能为空")
	private String flowName;

	@TableField("flow_code")
	@ApiModelProperty(value = "流程编码")
	@NotBlank(message = "流程编码不能为空")
	private String flowCode;

	@TableField("flow_version")
	@ApiModelProperty(value = "状态变更流程版本")
	@NotBlank(message = "状态变更流程版本不能为空")
	private String flowVersion;

	@TableField("status")
	@ApiModelProperty(value = "状态")
	@NotNull(message = "流程状态必选")
	private Boolean status;

	@TableField("remark")
	@ApiModelProperty(value = "备注描述")
	private String remark;
}
