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
 * 流程
 * 查询参数
 *
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("报价热度统计查询参数")
public class QuotationRecordViewParams extends BaseParams {
    @MatchType(value = QueryType.EQ, fieldName = {"viewTimeStart"})
    @ApiModelProperty("查看时间(开始),模糊查询")
    private String viewTimeStart;


    @MatchType(value = QueryType.EQ, fieldName = {"viewTimeEnd"})
    @ApiModelProperty("查看时间(结束),模糊查询")
    private String viewTimeEnd;
}
