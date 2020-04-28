package com.fykj.scaffold.evaluation.domain.vo;

import lombok.Data;

/**
 * <p>
 *  测试申请单评价
 * </p>
 */
@Data
public class ApplyNoteAppraiseVo {
//   满意度得分，5分为满分
    private String score;

//    得分文体
    private String scoreText;

    //    建议
    private String advise;
}
