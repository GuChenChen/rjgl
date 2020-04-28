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

/**
 * <p>
 * 字段类型
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_field_type")
public class FieldType extends BaseEntity {
    private static final long serialVersionUID = -945449754301749794L;

    /**
     * 编号
     */
    @Size(max = 20, message = "编号长度不能超过20个字符")
    @NotBlank(message = "编号不能为空")
    @ApiModelProperty(value = "编号", required = true)
    private String code;

    /**
     * 名称
     */
    @Size(max = 20, message = "名称长度不能超过20个字符")
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 是否是选择项
     */
    @NotNull(message = "是否是选择项必填")
    @ApiModelProperty(value = "是否是选择项", required = true)
    @TableField("is_options")
    private Boolean options;

    /**
     * 排序
     */
    @NotNull(message = "排序必填")
    @ApiModelProperty(value = "排序", required = true)
    @TableField("sequence")
    private Integer sequence;


}
