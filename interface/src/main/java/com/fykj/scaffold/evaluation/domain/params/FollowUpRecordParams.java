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
 * 流程
 * 查询参数
 *
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("跟进记录查询参数")
public class FollowUpRecordParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = -7866681619281465409L;

    @MatchType(value = QueryType.EQ, fieldName = {"evaluation_user_id"})
    @ApiModelProperty("单位id")
    private String evaluationUserId;
}
