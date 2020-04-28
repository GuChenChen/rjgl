package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.security.business.domain.entity.ApiParamManage;
import com.fykj.scaffold.security.business.domain.params.ApiParamManageParams;
import com.fykj.scaffold.security.business.service.impl.ApiParamManageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;

import java.util.List;

/**
 * @author yangx
 */
@RestController
@RequestMapping("/admin/apiParam")
@Api(tags = "接口管理")
public class ApiParamManageController extends BaseController<ApiParamManageServiceImpl, ApiParamManage, ApiParamManageParams> {

    @ApiOperation("根据接口id查询参数列表")
    @GetMapping(value = "/getParamListByUrlId")
    public JsonResult<List<ApiParamManage>> getParamListByUrlId(@RequestParam String urlId){
        List<ApiParamManage> apiParamList = baseService.getParamByUrlId(urlId);
        return new JsonResult<>(apiParamList);
    }
}
