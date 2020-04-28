package com.fykj.scaffold.security.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.User;
import com.fykj.scaffold.security.business.domain.params.UserParams;

import com.fykj.scaffold.security.business.service.impl.UserServiceImpl;
import com.fykj.scaffold.support.conns.DictCons;
import com.fykj.scaffold.support.excel.ExcelUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;

import java.util.List;


/**
 * <p>
 * 用户前端前端控制器
 * </p>
 *
 * @author wangf
 * @since 2019-10-16
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserController extends BaseController<UserServiceImpl, User, UserParams> {

    @Override
    public JsonResult<IPage<User>> list(@RequestBody(required = false)UserParams params) {
        SystemUtil.getUser();
        //写死只查询普通用户信息
        params.setType(DictCons.SYS_USER_ACCOUNT_TYPE_NORMAL);
        return super.list(params);
    }

    @GetMapping("loginUser")
    public JsonResult<BackendUserDetail> loginUser() {
        return new JsonResult<>(baseService.getUser());
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "editPassWord")
    public Result editPassWord(@RequestParam String oldPassWord, @RequestParam String newPassWord) {
        return baseService.editPassWord(oldPassWord, newPassWord);
    }

    @ApiOperation(value = "通过角色编码获得用户列表")
    @GetMapping(value = "/getByRoleCode")
    public JsonResult<List<User>> getByRoleCode(@RequestParam (required = false)String roleCode) {
        return new JsonResult<>(baseService.getByRoleCode(roleCode));
    }

    @ApiOperation(value="导出列表")
    @PostMapping(value = "/exportExcel")
    public void exportExcel(@RequestBody(required = false) UserParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        params.setType(DictCons.SYS_USER_ACCOUNT_TYPE_NORMAL);
        List<User> list = baseService.page(params).getRecords();
        ExcelUtil.fillExcel(list,"user","user.xlsx");
    }
}
