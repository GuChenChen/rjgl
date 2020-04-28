package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.cms.domain.entity.CmsContentAttachment;
import com.fykj.scaffold.cms.domain.params.CmsContentAttachParams;
import com.fykj.scaffold.cms.domain.vo.AttachVo;
import com.fykj.scaffold.cms.service.impl.CmsContentAttachmentServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import constants.Mark;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultCode;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/admin/cms/contentAttach")
public class CmsContentAttachmentController extends BaseController<CmsContentAttachmentServiceImpl, CmsContentAttachment,CmsContentAttachParams> {

    /**
     * 保存内容附件管理
     *
     * @param attachVo attachVo
     */
    @SysLogMethod("保存内容附件")
    @ApiOperation(value = "保存内容附件管理")
    @PostMapping(value = "/saveAttach")
    public Result saveAttach(@RequestBody @Validated AttachVo attachVo) {
        if (baseService.saveAttach(attachVo)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @SysLogMethod("批量删除")
    @ApiOperation("批量删除方法")
    @GetMapping(value = "/deleteByIds")
    public Result deleteByIds(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(Mark.COMMA));
        boolean result = baseService.deleteByIds(idList);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }
}
