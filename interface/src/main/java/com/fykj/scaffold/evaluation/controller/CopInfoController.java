package com.fykj.scaffold.evaluation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.CopInfo;
import com.fykj.scaffold.evaluation.domain.params.CopInfoParams;
import com.fykj.scaffold.evaluation.domain.vo.CopInfoVo;
import com.fykj.scaffold.evaluation.service.impl.CopInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * 
 *
 * 前端控制器
 * @author gcc
 * @email ${email}
 * @date 2020-04-23 15:04:34
 */
@RestController
@RequestMapping("/admin/copInfo")
@Api(tags = "企业信息管理")
public class CopInfoController extends BaseController<CopInfoServiceImpl, CopInfo, BaseParams> {

    @ApiOperation("保存/更新企业信息")
    @PostMapping(value = "/saveCopInfo")
    public Result saveCopInfo(@RequestBody(required = false) CopInfoParams params) {
        if (params == null) {
            params = new CopInfoParams();
        }
        //更新数据
        Boolean result = baseService.saveOrUpdateCopInfo(params);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("查询企业信息列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<CopInfoVo>> findByPage(@RequestBody(required = false) CopInfoParams params) {
        if (params == null) {
            params = new CopInfoParams();
        }
        //查询列表数据
        IPage<CopInfoVo> list = baseService.findByPage(params);
        return new JsonResult<>(list);
    }


}
