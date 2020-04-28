package com.fykj.scaffold.api;


import com.fykj.scaffold.cms.domain.dto.UserFieldValueDto;
import com.fykj.scaffold.cms.domain.entity.AdviceField;
import com.fykj.scaffold.cms.service.IAdviceFieldService;
import com.fykj.scaffold.cms.service.IUserFieldValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/api/advice")
@Api(tags = "客户端咨询/建议接口")
public class ApiAdviceController {

    @Autowired
    private IUserFieldValueService baseService;

    @Autowired
    private IAdviceFieldService adviceFieldService;

    @ApiOperation(value = "保存咨询建议内容")
    @PostMapping(value = "/save")
    public Result save(@RequestBody @Validated UserFieldValueDto userFieldValueDto) {
        if (baseService.saveUserFieldValue(userFieldValueDto)) {
            return new Result();
        }
        return new Result(ResultCode.FAIL);
    }

    @GetMapping("findByAdviceCode")
    @ApiModelProperty("获取咨询建议字段列表")
    public JsonResult<List<AdviceField>> findByAdviceCode(@RequestParam String adviceCode) {
        return new JsonResult<>(adviceFieldService.findByAdviceCode(adviceCode));
    }
}
