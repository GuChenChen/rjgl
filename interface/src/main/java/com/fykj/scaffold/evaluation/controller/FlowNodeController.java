package com.fykj.scaffold.evaluation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.entity.FlowNode;
import com.fykj.scaffold.evaluation.domain.params.FlowNodeParams;
import com.fykj.scaffold.evaluation.service.impl.FlowNodeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * 流程结点
 *
 * 前端控制器
 * @author yangx
 * @date 2020-02-25 13:11:46
 */
@RestController
@RequestMapping("/admin/flowNode")
@Api(tags = "流程结点管理")
public class FlowNodeController extends BaseController<FlowNodeServiceImpl, FlowNode, FlowNodeParams> {

    @ApiOperation(value = "保存更新方法")
    @PostMapping(value = "/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody FlowNode entity){
        boolean result = baseService.saveOrUpdate(entity);
        if(result){
            return OK;
        }else{
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    @ApiOperation("分页查询")
    @PostMapping(value = "/pages")
    public JsonResult<IPage<FlowNode>> list(@RequestBody(required = false)FlowNodeParams params) {
        IPage<FlowNode> result = baseService.findByPage(params);
        return new JsonResult<>(result);
    }
}
