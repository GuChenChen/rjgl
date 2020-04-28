package com.fykj.scaffold.security.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.security.business.domain.dto.ApiManageDto;
import com.fykj.scaffold.security.business.domain.entity.ApiManage;
import com.fykj.scaffold.security.business.domain.params.ApiManageParams;
import com.fykj.scaffold.security.business.service.impl.ApiManageServiceImpl;
import constants.Mark;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/api")
@Api(tags = "接口管理")
public class ApiManageController extends BaseController<ApiManageServiceImpl,ApiManage,ApiManageParams> {

    @ApiOperation("获取所要调的第三方api的code和参数(第三方返回content-type为text/plain)")
    @GetMapping(value = "/getApiOfCTIsTextPlain")
    public JsonResult getApiOfCTIsTextPlain(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, URISyntaxException {
        String entityStr = baseService.getParamsOfHttpRequestNeed(request);
        return new JsonResult<>(entityStr);
    }

    @ApiOperation("获取所要调的第三方api的code和参数(第三方返回content-type为application/json)")
    @GetMapping(value = "/getApiOfCTIsApplicationJson")
    public JSONObject getApiOfCTIsApplicationJson(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, URISyntaxException {
        String entityStr = baseService.getParamsOfHttpRequestNeed(request);
        return JSONObject.parseObject(entityStr);
    }

    @ApiOperation(value="分页条件查询第三方接口列表")
    @PostMapping(value = "/pageForApi")
    public JsonResult<IPage<ApiManageDto>> apiList(@RequestBody(required = false) ApiManageParams params) {
        if (params == null) {
            params = new ApiManageParams();
        }
        IPage<ApiManageDto> page = baseService.findByPage(params);
        return new JsonResult<>(page);
    }

    @ApiOperation("批量更改状态")
    @GetMapping(value = "/statusChangeByIds")
    public Result statusChangeByIds(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(Mark.COMMA));
        boolean result = baseService.statusChangeByIds(idList);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL.code(), "批量更改不成功！");
    }

    @Override
    @ApiOperation(value="通过接口id查询接口信息与参数信息")
    @GetMapping
    public Result get(@RequestParam String id) {
        return new JsonResult<>(baseService.getApiInfoAndParamInfo(id));
    }

    @ApiOperation(value = "保存接口信息与接口参数信息")
    @PostMapping(value = "/saveApiInfoAndParams")
    public Result save(@RequestBody ApiManageDto apiManageDto) {
        boolean result = baseService.saveApiInfoAndParams(apiManageDto);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation(value = "判断code是否重复")
    @GetMapping(value = "/validateCode")
    public Result validate(@RequestParam String code) {
        boolean result = baseService.validate(code);
        if (result) {
            return null;
        }
        return new Result(ResultCode.FAIL);
    }

    @Override
    @ApiOperation("批量删除方法")
    @GetMapping(value = "/removeByIds")
    public Result removeByIds(@RequestParam String ids) {
        List<String> idList = Arrays.asList(ids.split(Mark.COMMA));
        boolean result = baseService.removeBatch(idList);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }
}
