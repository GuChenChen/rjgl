package com.fykj.scaffold.cms.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 客户端视图模型
 *
 * @author feihj.
 * @date: 2020/4/1 13:12
 */
@Data
@ApiModel("客户端关于我们视图模型")
public class AboutUsVo implements Serializable {
    private static final long serialVersionUID = -1757367588511867999L;

    @ApiModelProperty(value = "公司介绍")
    private CmsContentClientsVo introduceVo;

    @ApiModelProperty(value = " 发展历程")
    private List<CmsContentClientsVo> developmentList;

    @ApiModelProperty(value = " 联系我们")
    private List<CmsContentClientsVo> linkUsList;


}
