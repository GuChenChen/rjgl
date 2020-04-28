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
 * @author wangming
 * @date 2020-04-07 14:26:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_send_record")
public class SmsSendRecord extends BaseEntity  {

	private static final long serialVersionUID = 1L;

	/**
	 * 发送人
	 */
	@TableField("send_id")
    @ApiModelProperty(value = "发送人")
	private String sendId;

	/**
	 * 发送人姓名
	 */
	@TableField("send_name")
    @ApiModelProperty(value = "发送人姓名")
	private String sendName;

	/**
	 * 测试单号
	 */
	@TableField("test_no")
    @ApiModelProperty(value = "测试单号")
	private String testNo;

	/**
	 * 接收人
	 */
	@TableField("receive_id")
    @ApiModelProperty(value = "接收人")
	private String receiveId;

	/**
	 * 接收人名称
	 */
	@TableField("receive_name")
    @ApiModelProperty(value = "接收人名称")
	private String receiveName;

	/**
	 * 手机号
	 */
	@TableField("mobile")
    @ApiModelProperty(value = "手机号")
	private String mobile;

	/**
	 * 短信内容
	 */
	@TableField("sms_content")
    @ApiModelProperty(value = "短信内容")
	private String smsContent;

	/**
	 * 发送状态(0:失败 1:成功)
	 */
	@TableField("status")
    @ApiModelProperty(value = "发送状态(0:失败 1:成功)")
	private Boolean status;


}
