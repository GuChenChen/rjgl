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
 * 系统日志查询参数
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("系统日志查询参数")
public class SysLogParams extends BaseParams {
    private static final long serialVersionUID = 2910158825212804441L;

    @ApiModelProperty("关键字查询，模糊匹配用户名/操作名")
    @MatchType(value = QueryType.LIKE, fieldName = {"username", "operation"})
    private String key;

    @ApiModelProperty("用户名查询")
    @MatchType(value = QueryType.EQ, fieldName = "username")
    private String userName;

    @ApiModelProperty("参数查询")
    @MatchType(value = QueryType.LIKE, fieldName = "params")
    private String params;
}
