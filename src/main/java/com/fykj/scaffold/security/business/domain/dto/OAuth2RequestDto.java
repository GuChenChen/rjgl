package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel
public class OAuth2RequestDto {
    @NotNull(message = "username必填")
    @ApiModelProperty(value = "")
    private String username;
    @NotNull(message = "password必填")
    @ApiModelProperty(value = "")
    private String password;
    @NotNull(message = "登录方式必填")
    @ApiModelProperty(value = "")
    private String loginType;
    @ApiModelProperty(value = "")
    private String enterpriseName;
}
