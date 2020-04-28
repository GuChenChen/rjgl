package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 *
 * @author wangm
 * @date 2019/8/21
 */
@Data
public class MessageBaseDto {

    public interface Other extends Default {}

    @ApiModelProperty(value = "手机号码")
    @NotNull(message = "手机号码必填")
    @Pattern(regexp = "1[3|4|5|7|8|9][0-9]\\d{8}",message = "手机号码格式不正确")
    private String mobile;

    /**
     * 模板所对应的code
     */
    @ApiModelProperty(value = "模板所对应的code")
    @NotNull(message = "code必填")
    private String code;
}
