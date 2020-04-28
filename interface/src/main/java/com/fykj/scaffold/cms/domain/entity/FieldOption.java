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
 * 咨询建议字段选项
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_field_option")
public class FieldOption extends BaseEntity {
    private static final long serialVersionUID = -4821259704482495L;

    /**
     * 咨询建议字段主键
     */
    @ApiModelProperty(value = "咨询建议字段主键")
    @TableField("field_id")
    private String fieldId;

    /**
     * 名称（对应选项显示内容）
     */
    @Size(max = 30, message = "名称长度不能超过30个字符")
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称（对应选项显示内容）", required = true)
    @TableField("name")
    private String name;

    /**
     * 值（对应选项值）
     */
    @Size(max = 30, message = "值长度不能超过30个字符")
    @NotBlank(message = "值不能为空")
    @ApiModelProperty(value = "值（对应选项值）", required = true)
    @TableField("value")
    private String value;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序", required = true)
    @TableField("sequence")
    private Integer sequence;


}
