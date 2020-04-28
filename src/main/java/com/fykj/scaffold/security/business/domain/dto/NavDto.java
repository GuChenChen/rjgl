package com.fykj.scaffold.security.business.domain.dto;

import com.fykj.scaffold.security.business.domain.entity.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 导航菜单和权限加载对象
 *
 * @author zhangzhi
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("导航菜单和权限加载对象模型")
public class NavDto implements Serializable {
    private static final long serialVersionUID = 8376396340664126651L;

    /**
     * 菜单列表
     */
    @ApiModelProperty(value = "菜单列表")
    private List<Resource> menuList;

    @ApiModelProperty(value = "授权编码列表")
    private List<String> permissions;

}
