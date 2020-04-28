package com.fykj.scaffold.security.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 *
 * @author wangm
 * @date 2019/8/21
 */
@ApiModel("短信模板实体类")
@Data
@EqualsAndHashCode(callSuper=false)
public class MessageDto extends MessageBaseDto {

    @ApiModelProperty(value = "如果是短信验证码，则必填过期时间，单位分钟")
    @NotNull(message = "验证码过期时间",groups = {VerifyCode.class})
    private Long expireTime;

    public interface VerifyCode extends Default {}

}
