package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.ScheduleJob;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-10-19
 */
public interface IScheduleJobService extends IService<ScheduleJob> {

    /**
     * 立即执行
     */
    void run(String jobId);

    /**
     * 暂停运行
     */
    void pause(String jobId);

    /**
     * 恢复运行
     */
    void resume(String jobId);
}
