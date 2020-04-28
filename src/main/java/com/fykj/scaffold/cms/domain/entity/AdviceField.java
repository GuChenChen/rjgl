package com.fykj.scaffold.cms.domain.entity;

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
import java.util.List;

/**
 * <p>
 * 咨询建议字段
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_advice_field")
public class AdviceField extends BaseEntity {
    private static final long serialVersionUID = -5465689535789933515L;

    /**
     * 编号
     */
    @Size(max = 30, message = "编号长度不能超过30个字符")
    @NotBlank(message = "编号不能为空")
    @ApiModelProperty(value = "编号", required = true)
    @TableField("code")
    private String code;

    /**
     * 名称
     */
    @Size(max = 30, message = "名称长度不能超过30个字符")
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    @TableField("name")
    private String name;

    /**
     * 字段类型编号
     */
    @NotBlank(message = "字段类型编号不能为空")
    @ApiModelProperty(value = "字段类型编号", required = true)
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "字段类型名称")
    @TableField(exist = false)
    private String typeName;

    /**
     * 是否必填
     */
    @NotNull(message = "是否必填不能为空")
    @ApiModelProperty(value = "是否必填", required = true)
    @TableField("is_required")
    private Boolean required;

    /**
     * 限制字数长度
     */
    @ApiModelProperty(value = "限制字数长度")
    @TableField("size_limit")
    private Integer sizeLimit;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序", required = true)
    @TableField("sequence")
    private Integer sequence;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField("remark")
    private String remark;

    /**
     * 是否是选择项（冗余）
     */
    @ApiModelProperty(value = "是否是选择项")
    @TableField("is_options")
    private Boolean options;

    /**
     * 咨询建议主键
     */
    @ApiModelProperty(value = "咨询建议主键")
    @TableField("advice_id")
    private String adviceId;

    /**
     * 咨询建议标题
     */
    @ApiModelProperty(value = "咨询建议标题")
    @TableField("advice_title")
    private String adviceTitle;

    /**
     * 字段选项列表
     */
    @ApiModelProperty(value = "字段选项列表")
    @TableField(exist = false)
    private List<FieldOption> optionList;

}
