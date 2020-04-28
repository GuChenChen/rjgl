package com.fykj.scaffold.security.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * key-value 类型视图模型
 *
 * @author zhangzhi
 */
@Data
@ApiModel("key-value 类型视图模型")
@NoArgsConstructor
@AllArgsConstructor
public class IdTextVo implements Serializable {
    private static final long serialVersionUID = -1757367588511867999L;

    @ApiModelProperty(value = "视图键-key")
    private String id;

    @ApiModelProperty(value = "视图值-value")
    private String text;

}