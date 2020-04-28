package com.fykj.scaffold.security.business.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 定时任务查询参数
 *
 * @author wangming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("定时任务查询参数")
public class ScheduleParams extends BaseParams {

    private static final long serialVersionUID = -1187659430759583279L;

    @MatchType(value = QueryType.LIKE, fieldName = "beanName")
    @ApiModelProperty("spring bean名称")
    private String beanName;

}
