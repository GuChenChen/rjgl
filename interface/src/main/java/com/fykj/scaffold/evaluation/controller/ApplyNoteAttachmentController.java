package com.fykj.scaffold.evaluation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNoteAttachment;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteAttachmentParams;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteAttachmentVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import com.fykj.scaffold.evaluation.service.IApplyNoteAttachmentService;
import com.fykj.scaffold.evaluation.service.impl.ApplyNoteAttachmentServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import com.fykj.scaffold.support.utils.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
 *  测试申请单附件前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/applyNoteAttachment")
@Api(tags = "测试申请单附件管理接口")
public class ApplyNoteAttachmentController extends BaseController<ApplyNoteAttachmentServiceImpl, ApplyNoteAttachment,BaseParams> {

    @Autowired
    private IApplyNoteAttachmentService iApplyNoteAttachmentService;

    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<ApplyNoteAttachmentVo>> page(@RequestBody(required = false) ApplyNoteAttachmentParams params) {
        SystemUtil.getUser();
        if (params == null) {
            params = new ApplyNoteAttachmentParams();
        }
        //查询列表数据
        IPage<ApplyNoteAttachmentVo> list = baseService.getListWithQuery(params);
        return new JsonResult<>(list);
    }

    @SysLogMethod("申请单附件上传")
    @ApiOperation("测试申请批量保存附件")
    @PostMapping(value = "/saveAttachmentList")
    public Result saveAttachmentList(@RequestBody List<ApplyNoteAttachment> applyNoteAttachmentList) {
        boolean result = iApplyNoteAttachmentService.saveAttachmentList(applyNoteAttachmentList);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

}
