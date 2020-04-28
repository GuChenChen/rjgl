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
 * 咨询建议查询参数
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("咨询建议查询参数")
public class AdviceParams extends BaseParams {
    private static final long serialVersionUID = 9059326476405057649L;

    @ApiModelProperty("名称，模糊查询")
    @MatchType(value = QueryType.LIKE)
    private String name;

    @ApiModelProperty("编码，模糊查询")
    @MatchType(value = QueryType.LIKE)
    private String code;

}
