package com.fykj.scaffold.weixin.mp.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wangf
 */
@Data
@ApiModel
public class JsapiSignatureDto {

    @ApiModelProperty(value = "服务号appid对应的code")
    @NotNull
    private String code;

    @ApiModelProperty(value = "当前页面的url")
    @JsonAlias("url")
    private String url;
}
