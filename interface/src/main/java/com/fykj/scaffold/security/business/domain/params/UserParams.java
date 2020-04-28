package com.fykj.scaffold.security.business.domain.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import com.fykj.scaffold.support.xss.annotation.XssFilterAttribute;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 用户查询参数
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("用户查询参数")
public class UserParams extends BaseParams {

    private static final long serialVersionUID = 5051351643065736048L;

    @ApiModelProperty("关键字查询，模糊匹配用户名、手机号、邮箱")
    @MatchType(value = QueryType.LIKE, fieldName = {"username", "mobile", "email"})
    private String keyword;

    @ApiModelProperty("账号，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "username")
    @XssFilterAttribute
    private String username;

    @ApiModelProperty("姓名，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "name")
    private String name;

    @ApiModelProperty("用户类型")
    @MatchType(value = QueryType.EQ, fieldName = "type")
    private String type;

    @ApiModelProperty("手机号，模糊查询")
    @MatchType(value = QueryType.LIKE, fieldName = "mobile")
    private String mobile;

    @ApiModelProperty("角色主键，精确查询")
    @MatchType(value = QueryType.EQ,fieldName = "role_id")
    private String roleId;

    @ApiModelProperty("激活状态，精确查询")
    @MatchType(value = QueryType.EQ,fieldName = "status")
    private Boolean status;

    @JsonIgnore
    @MatchType(value = QueryType.LIKE_RIGHT, fieldName = "role_linked")
    private String roleLinked;

}
