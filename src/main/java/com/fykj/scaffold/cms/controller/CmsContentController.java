package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.cms.domain.dto.AuditDto;
import com.fykj.scaffold.cms.domain.entity.CmsContent;
import com.fykj.scaffold.cms.service.impl.CmsContentServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-10-26
 */
@RestController
@RequestMapping("/admin/cms/content")
@Api(tags = "内容管理接口")
public class CmsContentController extends BaseController<CmsContentServiceImpl, CmsContent, BaseParams> {


    @SysLogMethod("内容审核")
    @ApiOperation(value = "审核")
    @PostMapping(value = "/audit")
    public Result audit(@RequestBody AuditDto dto) {
        if (baseService.audit(dto)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation(value = "根据标题模糊查询，用来做下拉搜索")
    @GetMapping(value = "/fuzzyQueryByTitle")
    public Result fuzzyQueryByTitle(String title) {
        return new JsonResult<>(baseService.fuzzyQueryByTitle(title));
    }

    @Override
    @ApiOperation("根据id获取")
    @GetMapping
    public Result get(@RequestParam String id) {
        return new JsonResult<>(baseService.getDetailById(id));
    }
}
