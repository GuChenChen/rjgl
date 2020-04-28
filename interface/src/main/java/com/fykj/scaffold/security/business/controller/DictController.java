package com.fykj.scaffold.security.business.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.domain.entity.Dict;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.security.business.service.impl.DictServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * <p>
 * 数据字典前端控制器
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
@RestController
@RequestMapping("/admin/dict")
@Api(tags = "数据字典")
public class DictController extends BaseController<DictServiceImpl, Dict, BaseParams> {

    @Autowired
    private IDictService dictService;

    @ApiOperation("保存数据字典")
    @PostMapping(value = "/save")
    @Override
    public Result save(@RequestBody @Validated({BaseEntity.Add.class}) Dict entity) {
        if (baseService.checkCodeExists(entity.getId(), entity.getCode())) {
            return new Result(ResultCode.FAIL.code(), "数据编码已存在");
        }
        boolean result = baseService.save(entity);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("更新数据字典")
    @PostMapping(value = "/update")
    @Override
    public Result update(@RequestBody @Validated({BaseEntity.Modify.class}) Dict entity) {
        if (baseService.checkCodeExists(entity.getId(), entity.getCode())) {
            return new Result(ResultCode.FAIL.code(), "数据编码已存在");
        }
        if (baseService.updateById(entity)) {
            return OK;
        }
        return new Result(ResultCode.DATA_EXPIRED);
    }


    @GetMapping("/code")
    public JsonResult<Dict> getByCode(@RequestParam String code) {
        return new JsonResult<>(dictService.getByCode(code));
    }

    @GetMapping("/parent")
    public JsonResult<List<Dict>> findByParentCode(@RequestParam String code) {
        List<Dict> dictList = dictService.findByParentCode(code);
        return new JsonResult<>(dictList);
    }

    @GetMapping("/findTopDict")
    public JsonResult<List<IdTextVo>> findTopDict() {
        List<IdTextVo> dictList = dictService.findTopDict();
        return new JsonResult<>(dictList);
    }
}
