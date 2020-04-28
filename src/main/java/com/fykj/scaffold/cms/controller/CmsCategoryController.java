package com.fykj.scaffold.cms.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.cms.domain.dto.CascaderDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategory;
import com.fykj.scaffold.cms.service.impl.CmsCategoryServiceImpl;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangm
 * @since 2019-10-25
 */
@RestController
@RequestMapping("/admin/cms/category")
@Api(tags = "栏目管理接口")
public class CmsCategoryController extends BaseController<CmsCategoryServiceImpl, CmsCategory, BaseParams> {

    @GetMapping("cascader")
    @ApiOperation("获取级联树")
    public JsonResult<List<CascaderDto>> cascader(){
        List<CascaderDto> categories = baseService.cascader();
        return new JsonResult<>(categories);
    }

    @GetMapping("tree")
    @ApiOperation("获取级联树")
    public JsonResult<List<CmsCategory>> tree(){
        List<CmsCategory> categories = baseService.tree();
        return new JsonResult<>(categories);
    }

    /**
     * 设置启用/禁用
     *
     * @param id id
     */
    @SysLogMethod("栏目启用禁用")
    @ApiOperation(value = "设置启用/禁用")
    @PostMapping(value = "/setEnable")
    public Result setEnable(@RequestParam String id) {
        if (baseService.setEnable(id)) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }
}
