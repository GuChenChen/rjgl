package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 *
 * @author wangm
 * @date 2019/8/21
 */
@ApiModel("短信验证码验证实体类")
@Data
@EqualsAndHashCode(callSuper=false)
public class ValidCodeDto extends MessageBaseDto {

    @ApiModelProperty(value="短信验证码")
    @NotNull(message = "短信验证码不能为空")
    private String verifyCode;

}
