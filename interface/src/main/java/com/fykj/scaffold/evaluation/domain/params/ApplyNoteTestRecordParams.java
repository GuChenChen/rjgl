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
@ApiModel("测试记录查询参数")
public class ApplyNoteTestRecordParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 8101810745441007613L;

    @MatchType(value = QueryType.EQ, fieldName = {"apply_note_id"})
    @ApiModelProperty("申请单主键")
    private String applyNoteId;

}
