package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAppraise;

/**
 * <p>
 *  测试申请单评价Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface ApplyNoteAppraiseMapper extends BaseMapper<ApplyNoteAppraise> {


    ApplyNoteAppraise findByApplyNoteId(String applyNoteId);
}
