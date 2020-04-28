package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 
 * </p>
 *
 * @author wangm
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsExtendedProp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("name")
    @NotBlank(message = "属性名称不能为空")
    @Size(max = 20, message = "属性名称不能超过20个字符")
    @ApiModelProperty(value = " 属性名称")
    private String name;

    /**
     * 状态（启用，未启用）
     */
    @TableField("status")
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = " 状态")
    private Boolean status;


}
