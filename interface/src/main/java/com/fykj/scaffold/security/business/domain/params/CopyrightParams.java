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
 * @author feihj.
 * @date: 2019/11/6 16:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("版权查询参数")
public class CopyrightParams extends BaseParams {

    private static final long serialVersionUID = -1187659430759583279L;



    @MatchType(value = QueryType.LIKE, fieldName = {"code"})
    @ApiModelProperty("所属网站")
    private String code;
    

}
