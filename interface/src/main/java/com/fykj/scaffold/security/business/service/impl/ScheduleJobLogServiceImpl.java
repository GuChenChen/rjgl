package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.ScheduleJobLog;
import com.fykj.scaffold.security.business.mapper.ScheduleJobLogMapper;
import com.fykj.scaffold.security.business.service.IScheduleJobLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-10-19
 */
@Service
@Transactional
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements IScheduleJobLogService {

}
