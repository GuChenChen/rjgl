package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.security.business.domain.entity.OssConfig;
import com.fykj.scaffold.security.business.service.IOssConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangf
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/admin/ossConfig")
public class OssConfigController {

    @Autowired
    private IOssConfigService ossConfigService;

    @ApiOperation("获取所有OSS配置")
    @GetMapping(value = "/getConfig")
    public JsonResult<OssConfig> getConfigs() {
        return new JsonResult<>(ossConfigService.getConfig());
    }

    @ApiOperation("保存oss配置")
    @PostMapping(value = "/save")
    public Result getConfigs(@Validated @RequestBody OssConfig config) {
        ossConfigService.saveOrUpdate(config);
        return new Result();
    }

}
