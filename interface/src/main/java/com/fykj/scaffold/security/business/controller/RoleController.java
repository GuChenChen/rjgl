package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.dto.RoleResourceDto;
import com.fykj.scaffold.security.business.domain.entity.Role;
import com.fykj.scaffold.security.business.domain.params.RoleParams;
import com.fykj.scaffold.security.business.service.impl.RoleServiceImpl;
import com.fykj.scaffold.security.oauth2.filter.Oauth2FilterInvocationSecurityMetadataSource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangf
 * @since 2019-10-16
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController extends BaseController<RoleServiceImpl, Role, BaseParams> {

    @Autowired
    private Oauth2FilterInvocationSecurityMetadataSource oauth2FilterInvocationSecurityMetadataSource;

    @PostMapping("/saveRole")
    @ApiOperation("保存角色资源")
    public Result save(@RequestBody RoleResourceDto dto) {
        if (baseService.checkCode(dto.getId(), dto.getCode())) {
            return new Result(ResultCode.FAIL.code(), "角色编码已存在");
        }
        boolean result = baseService.save(dto);
        if (result) {
            oauth2FilterInvocationSecurityMetadataSource.init();
            return new Result();
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation(value = "根据id 获取角色信息及菜单")
    @GetMapping(value = "/findOneById")
    public Result findOneById(@RequestParam String id) {
        return new JsonResult(baseService.findOneById(id));
    }

    @ApiOperation("分页查询")
    @PostMapping(value = "/list")
    public Result page(@RequestBody RoleParams params) {
        return new JsonResult<>(baseService.page(params));
    }
}
