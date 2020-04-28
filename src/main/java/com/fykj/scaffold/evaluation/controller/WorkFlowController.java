package com.fykj.scaffold.evaluation.controller;

import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.entity.WorkFlow;
import com.fykj.scaffold.evaluation.domain.params.WorkFlowParams;
import com.fykj.scaffold.evaluation.service.impl.WorkFlowServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import result.ResultCode;

/**
 * 流程
 *
 * 前端控制器
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@RestController
@RequestMapping("/admin/workFlow")
@Api(tags = "通知管理")
public class WorkFlowController extends BaseController<WorkFlowServiceImpl, WorkFlow, WorkFlowParams> {

    @ApiOperation(value = "保存更新方法")
    @PostMapping(value = "/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody WorkFlow entity){
        boolean result = baseService.saveOrUpdate(entity);
        if(result){
            return OK;
        }else{
            return new Result(ResultCode.FAIL);
        }
    }
}
