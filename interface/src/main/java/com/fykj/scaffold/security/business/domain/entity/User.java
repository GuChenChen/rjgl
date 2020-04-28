package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fykj.scaffold.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * <p>
 *
 * </p>
 *
 * @author wangf
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@Builder()
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Tolerate
    public User(){}

    /**
     * 登录账号
     */
    @TableField("username")
    private String username;

    /**
     * 密码    //TODO 加密不生效
     */
    @TableField("password")
    private String password;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 座机
     */
    @TableField("tel")
    private String tel;

    /**
     * 角色
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;

    /**
     * 员工id
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 显示名称（冗余字段）
     */
    @TableField("name")
    private String name;

    /**
     * 用户类型，普通或者客户注册账号
     */
    @TableField("type")
    private String type;

    @TableField(exist = false)
    private String roleName;

    /**
     * 角色编码链表
     */
    @JsonIgnore
    @TableField("role_linked")
    private String roleLinked;

    @TableField(exist = false)
    private String statusName;

    @TableField(exist = false)
    private String createDateText;
}
