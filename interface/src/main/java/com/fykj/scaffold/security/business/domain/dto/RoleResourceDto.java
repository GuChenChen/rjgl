package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author zhangzhi
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("角色资源权限批量保存数据模型")
public class RoleResourceDto implements Serializable {

    private static final long serialVersionUID = -5124778146803790994L;


    @ApiModelProperty(required = true, value = "角色主键")
    private String id;

    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(required = true, value = "角色编码")
    private String code;

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(required = true, value = "角色名称")
    private String name;

    @NotBlank(message = "角色状态不能为空")
    @ApiModelProperty(required = true, value = "角色状态")
    private boolean status;

    @NotBlank(message = "角色排序不能为空")
    @ApiModelProperty(required = true, value = "角色排序")
    private Integer sequence;

    /**
     * 资源主键列表（null或者空时删除角色全部资源）
     */
    @ApiModelProperty(value = "资源主键列表（null或者空时删除角色全部资源）")
    private List<String> menuIdList;

}
