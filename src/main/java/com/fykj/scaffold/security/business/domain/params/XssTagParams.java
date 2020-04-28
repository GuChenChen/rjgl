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
 * @author ZC
 * @date: 2019/10/30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("tag查询参数")
public class XssTagParams extends BaseParams {
    private static final long serialVersionUID = 5051351643065736048L;

    @ApiModelProperty("标签，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "tag")
    private String tag;

    @ApiModelProperty("属性，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "attribute_key")
    private String attributeKey;

    @ApiModelProperty("属性值，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "attribute_value")
    private String attributeValue;

    @ApiModelProperty("协议，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "protocol")
    private String protocol;
}
