package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.entity.ScheduleJobLog;
import com.fykj.scaffold.security.business.domain.params.ScheduleParams;
import com.fykj.scaffold.security.business.service.impl.ScheduleJobLogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/admin/scheduleLog")
@Api(tags = "定时任务日志")
public class ScheduleJobLogController extends BaseController<ScheduleJobLogServiceImpl, ScheduleJobLog, ScheduleParams> {

//    @ApiOperation("分页查询")
//    @PostMapping(value = "/list")
//    @Override
//    public Result list(@RequestBody ScheduleParams params) {
//        return super.list(params);
//    }
}
