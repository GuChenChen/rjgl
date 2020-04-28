package com.fykj.scaffold.evaluation.controller;

import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAttachment;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteTestRecordAttachment;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteTestRecordAttachmentVo;
import com.fykj.scaffold.evaluation.service.IApplyNoteTestRecordAttachmentService;
import com.fykj.scaffold.evaluation.service.impl.ApplyNoteTestRecordAttachmentServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * 
 *
 * 前端控制器
 * @author LiMingyang
 * @email ${email}
 * @date 2020-04-09 15:11:35
 */
@RestController
@RequestMapping("/admin/applyNoteTestRecordAttachment")
@Api(tags = "申请单测试记录测试文档附件接口")
public class ApplyNoteTestRecordAttachmentController extends BaseController<ApplyNoteTestRecordAttachmentServiceImpl, ApplyNoteTestRecordAttachment, BaseParams> {

    @Autowired
    private IApplyNoteTestRecordAttachmentService iApplyNoteTestRecordAttachmentService;

    @SysLogMethod("测试记录上传附件")
    @ApiOperation("申请单测试记录批量保存附件")
    @PostMapping(value = "/saveAttachmentList")
    public Result saveAttachmentList(@RequestBody List<ApplyNoteTestRecordAttachment> applyNoteTestRecordAttachments) {
//        boolean result = iApplyNoteTestRecordAttachmentService.saveBatch(applyNoteTestRecordAttachments);
        boolean result = iApplyNoteTestRecordAttachmentService.saveAttachmentList(applyNoteTestRecordAttachments);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("根据测试记录id获取所有附件列表")
    @GetMapping(value = "/getAttrByRecordId")
    public JsonResult<List<ApplyNoteTestRecordAttachmentVo>> getAttrByRecordId(@RequestParam String id){
        List<ApplyNoteTestRecordAttachmentVo> list = iApplyNoteTestRecordAttachmentService.getAttrByRecordId(id);
        return new JsonResult<>(list);
    }
}
