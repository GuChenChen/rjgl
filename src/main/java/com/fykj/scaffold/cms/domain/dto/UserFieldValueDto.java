package com.fykj.scaffold.cms.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("咨询建议模型")
public class UserFieldValueDto {

    @ApiModelProperty(value = "咨询建议id")
    private String adviceId;

    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "咨询建议字段值")
    private List<FieldValueDto> fieldValueList;
}
