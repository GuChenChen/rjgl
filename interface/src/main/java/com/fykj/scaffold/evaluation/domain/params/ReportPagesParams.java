package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户页面使用情况汇总
 * 查询参数
 *
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("用户页面使用情况汇总查询参数")
public class ReportPagesParams extends BaseParams  {

    @ApiModelProperty("单位ID")
    @MatchType(value = QueryType.EQ, fieldName = "evaluationUserId")
    private String evaluationUserId;
}
