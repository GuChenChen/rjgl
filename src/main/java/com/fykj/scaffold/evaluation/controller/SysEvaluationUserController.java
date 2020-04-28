package com.fykj.scaffold.evaluation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.domain.params.SysEvaluationUserParams;
import com.fykj.scaffold.evaluation.domain.vo.TestAccountListVo;
import com.fykj.scaffold.evaluation.service.impl.SysEvaluationUserServiceImpl;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.excel.ExcelUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangf
 * @since 2020-02-25
 */
@RestController
@RequestMapping("/admin/testAccount")
public class SysEvaluationUserController extends BaseController<SysEvaluationUserServiceImpl, SysEvaluationUser, SysEvaluationUserParams> {

    private DateTimeFormatter df = DateTimeFormatter.ofPattern(Cons.DATETIME_FORMAT);

    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<TestAccountListVo>> page(@RequestBody(required = false) SysEvaluationUserParams params) {
        SystemUtil.getUser();
        if (params == null) {
            params = new SysEvaluationUserParams();
        }
        //查询列表数据
        IPage<TestAccountListVo> list = baseService.getListWithQuery(params);
        return new JsonResult<>(list);
    }

    @ApiOperation("获取单位下拉")
    @GetMapping(value = "/getAll")
    public JsonResult<List<SysEvaluationUser>> getAll(){
        return new JsonResult<>(baseService.list());
    }


    @ApiOperation(value = "获取账户信息")
    @GetMapping(value = "/findDetail")
    public Result findDetail(@RequestParam String id) {
        return new JsonResult<>(baseService.findDetail(id));
    }

    @PostMapping("/saveTestAccount")
    @ApiOperation("保存账号信息")
    public Result saveTestAccount(@RequestBody TestAccountListVo vo) {
        boolean result = baseService.saveTestAccount(vo);
        if (result) {
            return new Result();
        }
        return new Result(ResultCode.FAIL);
    }

    @PostMapping("/updateTestAccount")
    @ApiOperation("修改账号信息")
    public Result updateTestAccount(@RequestBody TestAccountListVo vo) {
        boolean result = baseService.updateTestAccount(vo);
        if (result) {
            return new Result();
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation("删除账号")
    @GetMapping(value = "/remove")
    public Result delete(@RequestParam String id) {
        boolean result = baseService.delete(id);
        if (result) {
            return OK;
        }
        return new Result(ResultCode.FAIL);
    }

    @ApiOperation(value="导出列表")
    @PostMapping(value = "/exportExcel")
    public void exportExcel(@RequestBody(required = false) SysEvaluationUserParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<TestAccountListVo> list = baseService.getListWithQuery(params).getRecords();
        list.forEach(it->{
            it.setStatusName(it.isStatus()?"是":"否");
            it.setCreateDateText(ObjectUtils.isEmpty(it.getCreateDate())?null:df.format(it.getCreateDate()));
            it.setLastLoginDateText(ObjectUtils.isEmpty(it.getLastLoginDate())?null:df.format(it.getLastLoginDate()));
        });
        ExcelUtil.fillExcel(list,"test_account","test_account.xlsx");
    }

}
