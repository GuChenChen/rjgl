package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangm
 * @since 2019-11-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsCategoryProp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableField("category_id")
    @NotBlank(message = "栏目不能为空")
    @ApiModelProperty(value = " 栏目id")
    private String categoryId;

    /**
     * 扩展属性id
     */
    @TableField("prop_id")
    @NotBlank(message = "属性不能为空")
    @ApiModelProperty(value = " 扩展属性id")
    private String propId;

    /**
     * 扩展属性名称
     */
    @TableField(exist = false)
    @ApiModelProperty(value = " propName")
    private String propName;

}
