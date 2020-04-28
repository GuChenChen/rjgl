package com.fykj.scaffold.cms.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 审核模型
 *
 * @author wangming.
 * @date: 2019/9/2 9:30
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("级联选择模型")
public class CascaderDto implements Serializable {
    private static final long serialVersionUID = -6524844330606853296L;

    @ApiModelProperty(value = "id")
    private String value;

    @ApiModelProperty(value = "名称")
    private String label;

    @ApiModelProperty(value = "子节点")
    private List<CascaderDto> children;

}
