package com.fykj.scaffold.cms.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 审核模型
 *
 * @author wangming.
 * @date: 2019/9/2 9:30
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("审核模型")
public class AuditDto implements Serializable {
    private static final long serialVersionUID = -6524844330606853296L;

    @ApiModelProperty(value = "审核对象ids")
    private String ids;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态")
    private boolean status;

    @ApiModelProperty(value = "审核意见")
    private String checkOpinion;

}
