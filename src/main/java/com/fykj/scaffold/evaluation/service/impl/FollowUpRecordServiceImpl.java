package com.fykj.scaffold.evaluation.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.FollowUpRecord;
import com.fykj.scaffold.evaluation.mapper.FollowUpRecordMapper;
import com.fykj.scaffold.evaluation.service.IFollowUpRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  跟进记录服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class FollowUpRecordServiceImpl extends BaseServiceImpl<FollowUpRecordMapper, FollowUpRecord> implements IFollowUpRecordService {

    @Override
    public boolean save(FollowUpRecord entity) {
        entity.setInputDate(LocalDateTime.now());
        return super.save(entity);
    }

}
