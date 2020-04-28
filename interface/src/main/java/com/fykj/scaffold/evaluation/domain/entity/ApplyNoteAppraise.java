package com.fykj.scaffold.evaluation.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 测试申请单评价实体
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApplyNoteAppraise extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 申请单主键
     */
    @TableField("apply_note_id")
    @ApiModelProperty(value = "申请单主键")
    private String applyNoteId;

    /**
     * 得分（字典）
     */
    @TableField("score")
    @ApiModelProperty(value = "得分（字典）")
    private String score;

    /**
     * 得分文本
     */
    @TableField("score_text")
    @ApiModelProperty(value = "得分文本")
    private String scoreText;

    /**
     * 建议
     */
    @TableField("advise")
    @ApiModelProperty(value = "建议")
    private String advise;

    /**
     * 评价单位
     */
    @TableField("evaluation_user_id")
    @ApiModelProperty(value = "评价单位")
    private String evaluationUserId;


}
