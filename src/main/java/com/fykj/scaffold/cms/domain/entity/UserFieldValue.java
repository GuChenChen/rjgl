package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户咨询建议字段值
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_field_value")
public class UserFieldValue extends BaseEntity {
    private static final long serialVersionUID = 3205797170038995560L;

    /**
     * 用户主键
     */
    @TableField("user_id")
    private String userId;

    /**
     * 咨询建议主键
     */
    @TableField("advice_id")
    private String adviceId;

    /**
     * 咨询建议字段主键
     */
    @TableField("field_id")
    private String fieldId;

    /**
     * 值（对应选项值）
     */
    @TableField("value")
    private String value;

    /**
     * 数据id
     */
    @TableField("data_id")
    private String dataId;

}
