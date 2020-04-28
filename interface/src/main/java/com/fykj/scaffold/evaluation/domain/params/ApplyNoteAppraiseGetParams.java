package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("获取测试申请评分参数")
public class ApplyNoteAppraiseGetParams {
    private static final long serialVersionUID = 8101810745441007613L;

    @MatchType(value = QueryType.EQ, fieldName = {"apply_note_id"})
    @ApiModelProperty("申请单主键")
    private String applyNoteId;

}
