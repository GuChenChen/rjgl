package com.fykj.scaffold.evaluation.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.SmsSendRecord;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.mapper.SmsSendRecordMapper;
import com.fykj.scaffold.evaluation.service.ISmsSendRecordService;
import com.fykj.scaffold.evaluation.service.ISysEvaluationUserService;
import com.fykj.scaffold.security.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 *
 * 服务实现类
 * @author wangming
 * @date 2020-04-07 14:26:27
 */
@Service
public class SmsSendRecordServiceImpl extends BaseServiceImpl<SmsSendRecordMapper, SmsSendRecord> implements ISmsSendRecordService {

    @Autowired
    private ISysEvaluationUserService evaluationUserService;
    @Autowired
    private IUserService userService;

    @Override
    public boolean save(SmsSendRecord entity){
        SysEvaluationUser evaluationUser = evaluationUserService.getById(entity.getReceiveId());
        entity.setReceiveName(evaluationUser == null?"":evaluationUser.getCompanyName());
        entity.setSendName(userService.getNameById(entity.getSendId()));
        return super.save(entity);
    }
}