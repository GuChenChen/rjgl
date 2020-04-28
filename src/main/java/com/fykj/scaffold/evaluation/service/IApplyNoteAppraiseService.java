package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAppraise;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAppraiseParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAppraiseVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteDetailVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordVo;

import java.util.List;

/**
 * <p>
 *  测试申请单评价服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IApplyNoteAppraiseService extends IService<ApplyNoteAppraise> {
     void insert(ApplyNoteAppraiseParams params);

     ApplyNoteAppraiseVo findByApplyNoteId(String ApplyNoteId);

    //测试记录
     List<ApplyNoteTestRecordVo> getApplyNoteTestRecord(ApplyNoteTestRecordParams params);

   // 测试项目
     List<ApplyNoteAttachmentVo> getApplyNoteAttachment(ApplyNoteAttachmentParams params);

    //   测试申请单详情
    ApplyNoteDetailVo getInformationDetail(String id);

    // 保存附件
    Boolean saveApplyNoteAttachment(ApplyNoteAttachmentParams params);

}
