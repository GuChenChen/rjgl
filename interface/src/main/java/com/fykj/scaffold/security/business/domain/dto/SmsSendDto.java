package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class SmsSendDto {

    @ApiModelProperty(value = "手机号码")
    @NotNull(message = "手机号码必填")
    @Pattern(regexp = "1[3|4|5|7|8|9][0-9]\\d{8}",message = "手机号码格式不正确")
    private String mobile;

    /**
     * 配置文件中模板对应的code
     */
    @ApiModelProperty(value = "配置文件中模板对应的code")
    @NotNull(message = "code必填")
    private String code;

    /**
     * 短信消息中对应的变量值，按顺序存放
     */
    @ApiModelProperty(value = "短信消息中对应的变量值，按短信模板中变量值的顺序存放，不是必填")
    private List<String> variableValues = new ArrayList<>();
}
