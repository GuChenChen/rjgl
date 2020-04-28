package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 查询参数
 *
 * @author wangming
 * @date 2020-04-07 14:26:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SmsSendRecordParams extends BaseParams {

    @ApiModelProperty("过期开始时间")
    @MatchType(value = QueryType.TIME_START, fieldName = "create_date")
    private String startDate;

    @ApiModelProperty("过期结束时间")
    @MatchType(value = QueryType.TIME_END, fieldName = "create_date")
    private String endDate;
}
