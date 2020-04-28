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

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("测试申请查询参数")
public class ApplyNoteParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 8101810745441007613L;

    @ApiModelProperty("关键字查询，模糊匹配申请单号、申请单位名称、测试项目名称、手机号")
    @MatchType(value = QueryType.LIKE, fieldName = {"code", "evaluation_user_name","project_name", "tel"})
    private String keyword;

    @MatchType(value = QueryType.EQ, fieldName = {"status"})
    @ApiModelProperty("申请状态")
    private String status;

    @ApiModelProperty("提交开始时间")
    @MatchType(value = QueryType.DATE_START)
    private String startDate;

    @ApiModelProperty("提交结束时间")
    @MatchType(value = QueryType.DATE_END)
    private String endDate;

    @ApiModelProperty("申请单位主键")
    @MatchType(value = QueryType.EQ, fieldName = {"evaluation_user_id"})
    private String evaluationUserId;
}
