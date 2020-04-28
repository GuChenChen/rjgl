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
@NoArgsConstructor
@ApiModel("测试申请评分参数")
public class ApplyNoteAppraiseParams  {
    private static final long serialVersionUID = 8101810745441007613L;

    @MatchType(value = QueryType.EQ, fieldName = {"apply_note_id"})
    @ApiModelProperty("申请单主键")
    private String applyNoteId;

    @MatchType(value = QueryType.EQ, fieldName = {"score"})
    @ApiModelProperty("得分（字典）") //   满意度得分，5分为满分
    private String score;

    @MatchType(value = QueryType.EQ, fieldName = {"score_text"})
    @ApiModelProperty("得分文本")
    private String scoreText;

    @MatchType(value = QueryType.EQ, fieldName = {"advise"})
    @ApiModelProperty("建议")
    private String advise;
}
