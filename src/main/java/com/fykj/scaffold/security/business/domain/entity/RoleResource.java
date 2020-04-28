package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 角色资源中间表
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_resource")
public class RoleResource extends BaseEntity {
    private static final long serialVersionUID = 3258936649173644078L;

    /**
     * 角色主键
     */
    @NotBlank(message = "角色不能为空")
    private String roleId;

    /**
     * 资源主键
     */
    @NotBlank(message = "资源不能为空")
    private String resourceId;

    /**
     * 资源主键
     */
    private String roleName;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源路径
     */
    private String resourceUrl;

    /**
     * 是否半选状态
     */
    @TableField("is_half_checked")
    private boolean halfChecked;


}
