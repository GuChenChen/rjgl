package com.fykj.scaffold.cms.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 内容扩展属性查询参数
 *
 * @author wangming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("内容扩展属性查询参数")
public class CmsContentPropParams extends BaseParams {
    private static final long serialVersionUID = 7173982776985028514L;

    @ApiModelProperty("内容id，精确查询")
    @MatchType
    private String contentId;
}
