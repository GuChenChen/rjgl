package com.fykj.scaffold.cms.domain.dto;

import com.fykj.scaffold.cms.domain.entity.CmsContentProp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangming
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("扩展属性模型")
public class PropValueDto {

    /**
     * 栏目id
     */
    @ApiModelProperty(value = "栏目id")
    private String categoryId;

    /**
     * 内容id
     */
    @ApiModelProperty(value = "内容id")
    private String contentId;

    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id")
    private String propId;

    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private String propValue;

    /**
     * 是否和栏目相关
     */
    @ApiModelProperty(value = "是否和栏目相关")
    private Boolean categoryRelate;

    private List<CmsContentProp> propValueList = new ArrayList<>();
}
