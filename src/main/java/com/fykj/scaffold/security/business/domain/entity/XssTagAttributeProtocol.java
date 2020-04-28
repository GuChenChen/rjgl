package com.fykj.scaffold.security.business.domain.entity;

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
 * @author zhaoc
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class XssTagAttributeProtocol extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名
     */
    @TableField("tag")
    private String tag;

    /**
     * 属性名
     */
    @TableField("attribute_key")
    private String attributeKey;

    /**
     * 属性值
     */
    @TableField("attribute_value")
    private String attributeValue;

    /**
     * 协议值
     */
    @TableField("protocol")
    private String protocol;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;
}
