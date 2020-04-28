package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.dto.NavDto;
import com.fykj.scaffold.security.business.domain.entity.Resource;
import com.fykj.scaffold.security.business.service.impl.ResourceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * <p>
 * 资源前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@RestController
@RequestMapping("/admin/resource")
@Api(tags = "资源管理接口")
public class ResourceController extends BaseController<ResourceServiceImpl, Resource, BaseParams> {

    @ApiOperation("获取用户菜单列表")
    @GetMapping(value = "menus")
    public JsonResult<NavDto> menus() {
        return new JsonResult<>(baseService.menus());
    }

    @ApiOperation("获取用户按钮列表")
    @GetMapping(value = "buttons")
    public JsonResult<List<Resource>> buttons(String menuId) {
        return new JsonResult<>(baseService.buttons(menuId));
    }

    @ApiOperation("获取全部资源树")
    @GetMapping(value = "tree")
    public JsonResult<List<Resource>> tree() {
        List<Resource> resources = baseService.tree(null);
        return new JsonResult<>(resources);
    }

    @ApiOperation("获取指定角色资源树")
    @GetMapping(value = "tree/role")
    public JsonResult<List<Resource>> tree(@RequestParam String roleId) {
        List<Resource> resources = baseService.tree(roleId);
        return new JsonResult<>(resources);
    }

    @ApiOperation("获取指定角色资源列表")
    @GetMapping(value = "list/role")
    public JsonResult<List<Resource>> list(@RequestParam String roleId) {
        List<Resource> resources = baseService.findByRoleId(roleId);
        return new JsonResult<>(resources);
    }

}
