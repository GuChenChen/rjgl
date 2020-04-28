package com.fykj.scaffold.cms.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel("咨询建议字段值模型")
public class FieldValueDto {

    @ApiModelProperty(value = "咨询建议id")
    private String adviceId;

    @ApiModelProperty(value = "咨询建议字段主键")
    private String fieldId;

    @ApiModelProperty(value = "值（对应选项值）")
    private String value;
}
