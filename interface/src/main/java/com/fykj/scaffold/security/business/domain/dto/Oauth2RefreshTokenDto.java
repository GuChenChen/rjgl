package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author wangf
 */
@Getter
@Setter
@ApiModel("刷新token参数对象")
public class Oauth2RefreshTokenDto {

    @NotNull(message = "refreshToken必填")
    @ApiModelProperty(value = "")
    private String refreshToken;
}
