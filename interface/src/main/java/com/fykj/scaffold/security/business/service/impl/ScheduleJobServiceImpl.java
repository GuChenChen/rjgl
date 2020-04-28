package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.ScheduleJob;
import com.fykj.scaffold.security.business.mapper.ScheduleJobMapper;
import com.fykj.scaffold.security.business.service.IScheduleJobService;
import com.fykj.scaffold.support.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-10-19
 */
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
       /* List<ScheduleJob> scheduleJobList = this.list();
        for(ScheduleJob scheduleJob : scheduleJobList){
            if (!scheduleJob.getStatus()) {
                continue;
            }
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getId());
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }
        }*/
    }

    @Override
    public boolean save(ScheduleJob entity){
        entity.setStatus(true);
        super.save(entity);
        //创建定时任务
        ScheduleUtils.createScheduleJob(scheduler, entity);
        return true;
    }

    @Override
    public boolean updateById(ScheduleJob entity){
        ScheduleJob scheduleJob = getById(entity.getId());
        entity.setStatus(scheduleJob.getStatus());
        if (entity.getStatus()) {
            //更新定时任务
            ScheduleUtils.deleteScheduleJob(scheduler, entity.getId());
            ScheduleUtils.createScheduleJob(scheduler, entity);
        }
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        ScheduleUtils.deleteScheduleJob(scheduler, id.toString());
        return super.removeById(id);
    }

    @Override
    public void run(String jobId) {
        ScheduleUtils.run(scheduler, this.getById(jobId));
    }

    @Override
    public void pause(String jobId) {
        //更新定时任务
        ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        ScheduleJob scheduleJob = getById(jobId);
        scheduleJob.setStatus(false);
        super.updateById(scheduleJob);
    }

    @Override
    public void resume(String jobId) {
        ScheduleJob scheduleJob = getById(jobId);
        scheduleJob.setStatus(true);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        super.updateById(scheduleJob);
    }
}
