package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangm
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsContentProp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 扩展属性id
     */
    @TableField("prop_id")
    private String propId;

    /**
     * 内容id
     */
    @TableField("content_id")
    private String contentId;

    /**
     * 属性值
     */
    @TableField("prop_value")
    private String propValue;

    /**
     * 是否和栏目相关
     */
    @TableField("is_category_relate")
    private Boolean categoryRelate;


}
