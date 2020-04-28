package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.entity.ScheduleJob;
import com.fykj.scaffold.security.business.domain.params.ScheduleParams;
import com.fykj.scaffold.security.business.service.impl.ScheduleJobServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import result.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-10-19
 */
@RestController
@RequestMapping("/admin/schedule")
@Api(tags = "定时任务")
public class ScheduleJobController extends BaseController<ScheduleJobServiceImpl, ScheduleJob, BaseParams> {

    /**
     * 立即执行任务
     */
    @GetMapping("/run")
    public Result run(@RequestParam String jobId){
        baseService.run(jobId);
        return new Result();
    }

    /**
     * 暂停定时任务
     */
    @GetMapping("/pause")
    public Result pause(@RequestParam String jobId){
        baseService.pause(jobId);

        return new Result();
    }

    /**
     * 恢复定时任务
     */
    @GetMapping("/resume")
    public Result resume(@RequestParam String jobId){
        baseService.resume(jobId);
        return new Result();
    }
}
