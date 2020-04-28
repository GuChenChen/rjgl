package com.fykj.scaffold.evaluation.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAppraise;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAppraiseParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteTestRecordParams;
import com.fykj.scaffold.evaluation.domain.vo.*;
import com.fykj.scaffold.evaluation.service.IApplyNoteService;
import com.fykj.scaffold.evaluation.service.impl.ApplyNoteAppraiseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * <p>
 *  测试申请单评价前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/applyNoteAppraise")
@Api(tags = "测试申请单评价管理接口与前端获取申请单列表、详情等接口")
public class ApplyNoteAppraiseController extends BaseController<ApplyNoteAppraiseServiceImpl, ApplyNoteAppraise,BaseParams> {

    @Autowired
    private IApplyNoteService applyNoteService;

    @ApiOperation("提交评价")
    @PostMapping(value = "/commitAppraise")
    public JsonResult commitAppraise(@RequestBody(required = false) ApplyNoteAppraiseParams params) {
        if (params == null) {
            params = new ApplyNoteAppraiseParams();
        }
        //插入数据
        baseService.insert(params);
        return new JsonResult(0,"评论成功");
    }

    @ApiOperation("获取评价")
    @PostMapping(value = "/getAppraise")
    public JsonResult<ApplyNoteAppraiseVo> getAppraise(@RequestBody String id) {
        //获取数据
        ApplyNoteAppraiseVo applyNoteAppraiseVo = baseService.findByApplyNoteId(id);
        return new JsonResult<>(applyNoteAppraiseVo);
    }

    @ApiOperation("前端根据登录用户获取测试申请记录列表")
    @PostMapping(value = "/findByLoginUser")
    public JsonResult<List<ApplyNoteListVo>> findByLoginUser() {
        //查询列表数据
        List<ApplyNoteListVo> byLoginUser = applyNoteService.findByLoginUser();
        return new JsonResult<>(byLoginUser);
    }

    @ApiOperation("前端测试记录查询")
    @PostMapping(value = "/getApplyNoteTestRecord")
    public JsonResult<List<ApplyNoteTestRecordVo>> getApplyNoteTestRecord(@RequestBody(required = false) ApplyNoteTestRecordParams params) {
        if (params == null) {
            params = new ApplyNoteTestRecordParams();
        }
        //查询列表数据
        List<ApplyNoteTestRecordVo> list = baseService.getApplyNoteTestRecord(params);
        return new JsonResult<>(list);
    }

    @ApiOperation("前端测试项目附件")
    @PostMapping(value = "/getApplyNoteAttachment")
    public JsonResult<List<ApplyNoteAttachmentVo>> getApplyNoteAttachment(@RequestBody(required = false) ApplyNoteAttachmentParams params) {
        if (params == null) {
            params = new ApplyNoteAttachmentParams();
        }
        //查询列表数据
        List<ApplyNoteAttachmentVo> list = baseService.getApplyNoteAttachment(params);
        return new JsonResult<>(list);
    }


    //    @SysLogMethod("查询")
    @ApiOperation("测试申请详情信息查询")
    @PostMapping(value = "/getInformationDetail")
    public JsonResult<ApplyNoteDetailVo> getInformationDetail(@RequestBody String id) {
        //查询列表数据
        ApplyNoteDetailVo informationDetail = baseService.getInformationDetail(id);
        return new JsonResult<>(informationDetail);
    }

    @ApiOperation("保存附件")
    @PostMapping(value = "/saveApplyNoteAttachment")
    public Result saveApplyNoteAttachment(@RequestBody(required = false) ApplyNoteAttachmentParams params) {
        if (params == null) {
            params = new ApplyNoteAttachmentParams();
        }
        //插入数据
        Boolean result = baseService.saveApplyNoteAttachment(params);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

}
