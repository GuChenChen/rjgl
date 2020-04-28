package com.fykj.scaffold.evaluation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.evaluation.domain.entity.Notice;
import com.fykj.scaffold.evaluation.domain.params.NoticeParams;
import com.fykj.scaffold.evaluation.service.impl.NoticeServiceImpl;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

/**
 * 通知
 *
 * 前端控制器
 * @author wangming
 * @email ${email}
 * @date 2020-02-25 13:11:46
 */
@RestController
@RequestMapping("/admin/notice")
@Api(tags = "通知管理")
public class NoticeController extends BaseController<NoticeServiceImpl, Notice,NoticeParams> {

    @SysLogMethod("新增")
    @ApiOperation("保存方法")
    @PostMapping(value = "/save")
    @Override
    public Result save(@RequestBody @Validated({BaseEntity.Add.class}) Notice entity) {
        if (baseService.checkCode(entity.getId(), entity.getCode())) {
            return new Result(ResultCode.FAIL.code(), "节点编码已存在");
        }
        boolean result = baseService.save(entity);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }


    /**
     * 根据id更新
     *
     * @param entity
     * @return
     */
    @SysLogMethod("编辑")
    @ApiOperation("更新方法")
    @PostMapping(value = "/update")
    @Override
    public Result update(@RequestBody @Validated({BaseEntity.Modify.class}) Notice entity) {
        if (baseService.checkCode(entity.getId(), entity.getCode())) {
            return new Result(ResultCode.FAIL.code(), "节点编码已存在");
        }
        if (baseService.updateById(entity)) {
            return OK;
        }
        return new Result(ResultCode.DATA_EXPIRED);
    }

    /**
     * 分页查询
     *
     * @param params 查询条件封装{@link NoticeParams}
     * @return 分页结果
     */
    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<Notice>> page(@RequestBody(required = false) NoticeParams params) {
        if (params == null) {
            params = new NoticeParams();
        }
        //查询列表数据
        Page<Notice> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        IPage<Notice> list = baseService.getListWithQuery(page,params);
        return new JsonResult<>(list);
    }
}
