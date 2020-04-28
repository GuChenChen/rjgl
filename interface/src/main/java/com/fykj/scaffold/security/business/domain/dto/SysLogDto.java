package com.fykj.scaffold.security.business.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.security.business.domain.entity.ApiParamManage;
import com.fykj.scaffold.support.conns.Cons;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yangx
 */
@Data
@NoArgsConstructor
@ApiModel("第三方接口管理数据模型")
public class SysLogDto {

    @ApiModelProperty(value =  "操作记录id")
    private String id;

    @ApiModelProperty(value =  "操作人")
    private String operator;

    @ApiModelProperty(value =  "手机号")
    private String mobile;

    @ApiModelProperty(value =  "操作时间")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime operateDate;

    @ApiModelProperty(value =  "操作")
    private String operation;

    @ApiModelProperty(value =  "备注")
    private String remark;
}
