/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.fykj.scaffold.support.utils;

import com.fykj.scaffold.security.business.domain.entity.ScheduleJob;
import com.fykj.scaffold.security.business.domain.entity.ScheduleJobLog;
import com.fykj.scaffold.security.business.service.IScheduleJobLogService;
import com.fykj.scaffold.security.business.service.IScheduleJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;


/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ScheduleJobBean extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
        		.get(ScheduleJob.JOB_PARAM_KEY);

		//获取spring bean
		IScheduleJobService scheduleJobService = SystemUtil.getBean(IScheduleJobService.class);
		ScheduleJob entity = scheduleJobService.getById(scheduleJob.getId());
		if (!entity.getStatus()) {
			return;
		}
        //获取spring bean
		IScheduleJobLogService scheduleJobLogService = SystemUtil.getBean(IScheduleJobLogService.class);
        
        //数据库保存执行记录
		ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(entity.getId());
        log.setBeanName(entity.getBeanName());
        log.setParams(entity.getParams());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	logger.debug("任务准备执行，任务ID：" + entity.getId());

			Object target = SystemUtil.getBean(entity.getBeanName());
			Method method = target.getClass().getDeclaredMethod("run", String.class);
			method.invoke(target, entity.getParams());
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(true);
			
			logger.debug("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			log.setStatus(false);
			log.setError(e.toString().substring(0,2000));
		}finally {
			scheduleJobLogService.save(log);
		}
    }
}
