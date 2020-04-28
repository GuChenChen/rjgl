package com.fykj.scaffold.security.business.domain.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 角色查询参数
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("角色查询参数")
public class RoleParams extends BaseParams {

    private static final long serialVersionUID = -1187659430759583279L;

    @MatchType(value = QueryType.LIKE, fieldName = {"name", "code"})
    @ApiModelProperty("角色名称模糊查询")
    private String name;

    @ApiModelProperty("接口状态，精确查询")
    @MatchType(fieldName = "status")
    private Boolean status;

    @JsonIgnore
    @MatchType(value = QueryType.LIKE_RIGHT, fieldName = "role_linked")
    private String roleLinked;

}
