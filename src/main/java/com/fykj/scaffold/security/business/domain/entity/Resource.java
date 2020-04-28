package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseTreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单，接口，按钮
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_resource")
public class Resource extends BaseTreeEntity {
    private static final long serialVersionUID = 4679862647516119691L;

    /**
     * 授权(多个用逗号分隔，如：user-list,user-create)
     */
    @ApiModelProperty(value = "资源授权")
    private String code;

    /**
     * 资源名称
     */
    @NotBlank(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称", required = true)
    private String name;

    /**
     * 菜单的顺序
     */
    @ApiModelProperty(value = "资源排序")
    private Integer sequence;

    /**
     * 前端路由
     */
    @ApiModelProperty(value = "前端路由")
    private String url;

    /**
     * 类型 0：目录 1：菜单 2：按钮
     */
    @NotNull(message = "资源类型不能为空")
    @ApiModelProperty(value = "资源类型", required = true)
    private Integer type;

    @TableField(exist = false)
    @ApiModelProperty(value = "资源类型文本")
    private String typeText;

    /**
     * 后台权限管理路径
     */
    @NotBlank(message = "后台接口地址不能为空")
    @ApiModelProperty(value = "后台接口地址", required = true)
    private String path;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 下级资源列表
     */
    @ApiModelProperty(value = "下级资源列表")
    @TableField(exist = false)
    private List<Resource> children = new ArrayList<>();


}