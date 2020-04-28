package com.fykj.scaffold.cms.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 扩展属性视图模型
 *
 * @author wangming
 */
@Data
@ApiModel("扩展属性视图模型")
public class PropVo {

    @ApiModelProperty(value = "内容属性id")
    private String id;

    @ApiModelProperty(value = "属性id")
    private String propId;

    @ApiModelProperty(value = "属性名称")
    private String propName;

    @ApiModelProperty(value = "属性值")
    private String propValue;

}
