package com.fykj.scaffold.security.business.domain.dto;

import com.fykj.scaffold.security.business.domain.entity.ApiParamManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yangx
 */
@Data
@NoArgsConstructor
@ApiModel("第三方接口管理数据模型")
public class ApiManageDto {

    @ApiModelProperty(value =  "第三方接口id")
    private String id;

    @ApiModelProperty(value =  "第三方接口code")
    private String code;

    @ApiModelProperty(value =  "第三方接口url")
    private String url;

    @ApiModelProperty(value =  "第三方接口请求方式")
    private String method;

    @ApiModelProperty(value =  "第三方接口请求方式文本")
    private String methodText;

    @ApiModelProperty(value =  "第三方接口参数类型")
    private String paramType;

    @ApiModelProperty(value =  "第三方接口参数类型文本")
    private String paramTypeText;

    @ApiModelProperty(value =  "第三方接口返回类型")
    private String responseType;

    @ApiModelProperty(value =  "第三方接口返回类型文本")
    private String responseTypeText;

    @ApiModelProperty(value =  "第三方接口描述")
    private String mark;

    @ApiModelProperty(value =  "第三方接口状态（启用/禁用）")
    private Boolean status;

    @ApiModelProperty(value =  "第三方接口参数数量")
    private Integer paramNumber;

    @ApiModelProperty(value =  "第三方接口参数集")
    private List<ApiParamManage> apiParamManageList;

    @ApiModelProperty(value =  "版本")
    private Integer version;

}
