package com.fykj.scaffold.security.business.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yangx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("第三方接口查询参数")
public class ApiManageParams extends BaseParams {

    private static final long serialVersionUID = 4960395956054065839L;

    @ApiModelProperty("关键字查询，模糊匹配第三方接口code,接口描述")
    @MatchType(value = QueryType.LIKE, fieldName = {"code", "mark"})
    private String keyword;

    @ApiModelProperty("接口请求方式，精确查询")
    @MatchType(fieldName = "method")
    private String method;

    @ApiModelProperty("接口状态，精确查询")
    @MatchType(fieldName = "status")
    private Boolean status;
}
