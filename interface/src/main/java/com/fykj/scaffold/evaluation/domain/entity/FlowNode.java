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
 * 流程节点
 *
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_node")
public class FlowNode extends BaseEntity  {


	private static final long serialVersionUID = -6266680117245358006L;

	@TableField("flow_code")
    @ApiModelProperty(value = "所属流程编码")
	@NotBlank(message = "所属流程编码不能为空")
	private String flowCode;

	@TableField("origin_status")
    @ApiModelProperty(value = "原始状态编码（前驱）")
	private String originStatus;

	@TableField("action")
    @ApiModelProperty(value = "触发动作（当前）")
	@NotBlank(message = "节点编码不能为空")
	private String action;

	@TableField("action_name")
	@ApiModelProperty(value = "触发动作（当前）")
	@NotBlank(message = "节点名称不能为空")
	private String actionName;

	@TableField("target_status")
    @ApiModelProperty(value = "目标状态编码（后继）")
	private String targetStatus;

	@TableField("handle_role_code")
    @ApiModelProperty(value = "处理角色编码")
	private String handleRoleCode;

	@TableField("handle_user_id")
	@ApiModelProperty(value = "处理人主键")
	private String handleUserId;

	@TableField("sequence")
    @ApiModelProperty(value = "节点序号")
	@NotNull(message = "节点序号不能为空")
	private Integer sequence;

	@TableField("notice_id")
	@ApiModelProperty(value = "通知id")
	private String noticeId;

	@TableField("remark")
    @ApiModelProperty(value = "备注介绍")
	private String remark;
}
