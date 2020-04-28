package com.fykj.scaffold.evaluation.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAppraise;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAttachment;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAppraiseParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAppraiseVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteDetailVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordVo;
import com.fykj.scaffold.evaluation.mapper.ApplyNoteAppraiseMapper;
import com.fykj.scaffold.evaluation.service.*;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.support.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.StringUtil;

import java.util.List;

/**
 * <p>
 *  测试申请单评价服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class ApplyNoteAppraiseServiceImpl extends BaseServiceImpl<ApplyNoteAppraiseMapper, ApplyNoteAppraise> implements IApplyNoteAppraiseService {

    @Autowired
    private ISysEvaluationUserService sysEvaluationUserService;

    @Autowired
    private IApplyNoteTestRecordService applyNoteTestRecordService;

    @Autowired
    private IApplyNoteAttachmentService applyNoteAttachmentService;

    @Autowired
    private IApplyNoteService applyNoteService;

    @Override
    public void insert(ApplyNoteAppraiseParams params) {
        ApplyNoteAppraise applyNoteAppraise = new ApplyNoteAppraise();
        applyNoteAppraise.setApplyNoteId(params.getApplyNoteId());
        applyNoteAppraise.setScore(params.getScore());
        applyNoteAppraise.setScoreText(params.getScoreText());
        applyNoteAppraise.setAdvise(params.getAdvise());
        BackendUserDetail user = SystemUtil.getUser();
       //登陆用户存单位ID
        if (!StringUtils.isEmpty(user)){
            applyNoteAppraise.setEvaluationUserId(sysEvaluationUserService.getByLoginUser().getId());
        }
        baseMapper.insert(applyNoteAppraise);
    }

    @Override
    public ApplyNoteAppraiseVo findByApplyNoteId(String ApplyNoteId) {
        ApplyNoteAppraiseVo applyNoteAppraiseVo = new ApplyNoteAppraiseVo();
        ApplyNoteAppraise applyNoteAppraise = baseMapper.findByApplyNoteId(ApplyNoteId);
        if (StringUtil.isNotEmpty(applyNoteAppraise)) {
            applyNoteAppraiseVo.setScore(applyNoteAppraise.getScore());
            applyNoteAppraiseVo.setScoreText(applyNoteAppraise.getScoreText());
            applyNoteAppraiseVo.setAdvise(applyNoteAppraise.getAdvise());
        }else {
            return null;
        }
        return applyNoteAppraiseVo;
    }


    @Override
    public List<ApplyNoteTestRecordVo> getApplyNoteTestRecord(ApplyNoteTestRecordParams params) {
        List<ApplyNoteTestRecordVo> applyNoteTestRecord = applyNoteTestRecordService.getApplyNoteTestRecord(params);
        return applyNoteTestRecord;
    }

    @Override
    public List<ApplyNoteAttachmentVo> getApplyNoteAttachment(ApplyNoteAttachmentParams params) {
        List<ApplyNoteAttachmentVo> result = applyNoteAttachmentService.getApplyNoteAttachment(params);
        return result;
    }

    @Override
    public ApplyNoteDetailVo getInformationDetail(String id) {
        ApplyNoteDetailVo applyNoteDetailVo = applyNoteService.getInformationDetail(id);
        String type = applyNoteService.typeCovert(id);
        applyNoteDetailVo.setType(type);
        return applyNoteDetailVo;
    }

    @Override
    public Boolean saveApplyNoteAttachment(ApplyNoteAttachmentParams params) {
        ApplyNoteAttachment applyNoteAttachment = new ApplyNoteAttachment();
        applyNoteAttachment.setType(params.getType());
        applyNoteAttachment.setUrl(params.getUrl());
        applyNoteAttachment.setApplyNoteId(params.getApplyNoteId());
        applyNoteAttachment.setFileName(params.getFileName());
        applyNoteAttachment.setRemark(params.getRemark());
        return applyNoteAttachmentService.saveAttachment(applyNoteAttachment);
    }

}