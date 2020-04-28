package com.fykj.scaffold.security.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.security.business.domain.entity.XssTagAttributeProtocol;
import com.fykj.scaffold.security.business.domain.params.XssTagParams;
import com.fykj.scaffold.security.business.service.IXssTagAttributeProtocolService;
import com.fykj.scaffold.security.business.service.impl.XssTagAttributeProtocolServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;

/**
 * @author ZC
 * @date: 2019/10/30
 */
@RestController
@RequestMapping("/admin/whiteList/tags")
@Api(description = "xss白名单配置")
public class XssTagAttributeProtocolController extends BaseController<XssTagAttributeProtocolServiceImpl, XssTagAttributeProtocol,XssTagParams> {

    @Autowired
    IXssTagAttributeProtocolService xssTagAttributeProtocolService;

    @ApiOperation("分页查询")
    @PostMapping(value = "/pages")
    @Override
    public JsonResult<IPage<XssTagAttributeProtocol>> list(@RequestBody(required = false) XssTagParams params){
        return new JsonResult<>(xssTagAttributeProtocolService.findByPage(params));
    }

    @ApiOperation("新增")
    @PostMapping(value = "/save")
    @Override
    public Result save(@RequestBody XssTagAttributeProtocol info){
        xssTagAttributeProtocolService.saveInfo(info);
        return new JsonResult<>();
    }

    @ApiOperation("更新方法")
    @PostMapping(value = "/update")
    @Override
    public Result update(@RequestBody XssTagAttributeProtocol info){
        xssTagAttributeProtocolService.updateInfo(info);
        return new JsonResult<>();
    }
}
