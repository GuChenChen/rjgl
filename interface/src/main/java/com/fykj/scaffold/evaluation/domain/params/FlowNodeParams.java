package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 流程结点
 * 查询参数
 *
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("流程结点查询参数")
public class FlowNodeParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 8101810745441007613L;

    @MatchType(value = QueryType.EQ, fieldName = {"flow_code"})
    @ApiModelProperty("流程编码")
    private String flowCode;

}
