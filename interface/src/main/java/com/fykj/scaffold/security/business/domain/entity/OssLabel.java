package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_oss_label")
public class OssLabel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 对象存储id
     */
    @TableField("oss_id")
    private String ossId;

    /**
     * 标签id
     */
    @TableField("label_id")
    private String labelId;


}
