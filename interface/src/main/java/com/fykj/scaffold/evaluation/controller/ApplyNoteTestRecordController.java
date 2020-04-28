package com.fykj.scaffold.evaluation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecord;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import com.fykj.scaffold.evaluation.service.IApplyNoteTestRecordService;
import com.fykj.scaffold.evaluation.service.impl.ApplyNoteTestRecordServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import com.fykj.scaffold.support.utils.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * <p>
 *  申请单测试记录前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/applyNoteTestRecord")
@Api(tags = "申请单测试记录管理接口")
public class ApplyNoteTestRecordController extends BaseController<ApplyNoteTestRecordServiceImpl, ApplyNoteTestRecord, BaseParams> {

    @Autowired
    private IApplyNoteTestRecordService iApplyNoteTestRecordService;

    @SysLogMethod("修改测试记录信息")
    @ApiOperation("保存测试记录信息")
    @PostMapping(value = "/saveApplyNoteTestRecord")
    public JsonResult<ApplyNoteTestRecord> saveApplyNoteTestRecord(@RequestBody @Validated ApplyNoteTestRecord entity){
        ApplyNoteTestRecord applyNoteTestRecord = iApplyNoteTestRecordService.saveApplyNoteTestRecord(entity);
        return new JsonResult<>(applyNoteTestRecord);
    }

    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<ApplyNoteTestRecordVo>> page(@RequestBody(required = false) ApplyNoteTestRecordParams params) {
        SystemUtil.getUser();
        if (params == null) {
            params = new ApplyNoteTestRecordParams();
        }
        //查询列表数据
        IPage<ApplyNoteTestRecordVo> list = baseService.getListWithQuery(params);
        return new JsonResult<>(list);
    }

    @ApiOperation("获取所有测试记录")
    @PostMapping(value = "/getAllApplyNoteTestRecord")
    public JsonResult<List<ApplyNoteTestRecordVo>> getAllApplyNoteTestRecord(@RequestBody(required = false) ApplyNoteTestRecordParams params) {
        SystemUtil.getUser();
        if (params == null) {
            params = new ApplyNoteTestRecordParams();
        }
        //查询列表数据
        List<ApplyNoteTestRecordVo> list = baseService.getApplyNoteTestRecord(params);
        return new JsonResult<>(list);
    }

    @ApiOperation("根据id获取测试记录和对应附件信息")
    @GetMapping(value = "/getRecordInfoById")
    public Result getRecordInfoById(@RequestParam String id) {
        ApplyNoteTestRecordVo applyNoteTestRecordVo = iApplyNoteTestRecordService.getRecordInfoById(id);
        return new JsonResult<>(applyNoteTestRecordVo);
    }

    @SysLogMethod("修改测试记录信息")
    @ApiOperation("更新并删除附件")
    @PostMapping(value = "/updateRecordAndDelAtt")
    public Result updateRecordAndDelAtt(@RequestBody @Validated ApplyNoteTestRecord entity) {
        if (iApplyNoteTestRecordService.updateRecordAndDelAtt(entity)) {
            return OK;
        }
        return new Result(ResultCode.DATA_EXPIRED);
    }
}
