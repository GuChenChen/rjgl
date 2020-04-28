package com.fykj.scaffold.evaluation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ApplyNote;
import com.fykj.scaffold.evaluation.domain.params.ApplyNoteParams;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByApplyTimeVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos.ApplyNoteSumByStatusVo;
import com.fykj.scaffold.evaluation.domain.vo.ApplyNoteVo;
import com.fykj.scaffold.evaluation.service.IApplyNoteService;
import com.fykj.scaffold.evaluation.service.impl.ApplyNoteServiceImpl;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.excel.ExcelUtil;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import com.fykj.scaffold.support.utils.ExcelExportUtil;
import com.fykj.scaffold.support.utils.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  测试申请单 前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/applyNote")
@Api(tags = "测试申请单管理接口")
public class ApplyNoteController extends BaseController<ApplyNoteServiceImpl, ApplyNote,BaseParams> {

    private DateTimeFormatter df = DateTimeFormatter.ofPattern(Cons.DATETIME_FORMAT);

    @Autowired
    private IApplyNoteService iApplyNoteService;

    @ApiOperation("分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<ApplyNoteVo>> page(@RequestBody(required = false) ApplyNoteParams params) {
        SystemUtil.getUser();
        if (params == null) {
            params = new ApplyNoteParams();
        }
        //查询列表数据
        IPage<ApplyNoteVo> list = baseService.getListWithQuery(params);
        return new JsonResult<>(list);
    }

    @SysLogMethod("保存申请单")
    @ApiOperation("保存申请单")
    @PostMapping(value = "/saveApplyNote")
    public JsonResult<ApplyNote> saveApplyNote(@RequestBody @Validated ApplyNote entity){
        ApplyNote applyNote = iApplyNoteService.saveApplyNote(entity);
        return new JsonResult<>(applyNote);
    }

    @SysLogMethod("修改申请单")
    @ApiOperation("修改申请单")
    @PostMapping(value = "/updateApplyNote")
    public JsonResult<ApplyNote> updateApplyNote(@RequestBody @Validated ApplyNote entity){
        ApplyNote applyNote = iApplyNoteService.updateApplyNote(entity);
        return new JsonResult<>(applyNote);
    }

    @ApiOperation("测试申请统计-按状态")
    @PostMapping(value = "/sumByStatus")
    public JsonResult<ApplyNoteSumByStatusVo> sumByStatus(@RequestBody(required = false) ApplyNoteParams params) {
        if (params == null) {
            params = new ApplyNoteParams();
        }
        //查询列表数据
        ApplyNoteSumByStatusVo sum = baseService.sumByStatus(params);
        return new JsonResult<>(sum);
    }

    @ApiOperation("测试申请统计-按申请时间")
    @PostMapping(value = "/sumByApplyTime")
    public JsonResult<IPage<ApplyNoteSumByApplyTimeVo>> sumByApplyTime(@RequestBody(required = false) ApplyNoteParams params) {
        if (params == null) {
            params = new ApplyNoteParams();
        }
        //查询列表数据
        IPage<ApplyNoteSumByApplyTimeVo> page = baseService.sumByApplyTime(params);
        return new JsonResult<>(page);
    }
    @ApiOperation(value="导出列表")
    @PostMapping(value = "/exportExcel")
    public void exportExcel(@RequestBody(required = false) ApplyNoteParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<ApplyNoteVo> list = baseService.getListWithQuery(params).getRecords();
        ExcelUtil.fillExcel(list,"apply_note","apply_note.xlsx");
    }

    @ApiOperation(value="导出测试申请统计-按申请时间")
    @PostMapping(value = "/exportSumByApplyTime")
    public void exportSumByApplyTime(@RequestBody(required = false) ApplyNoteParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<ApplyNoteSumByApplyTimeVo> list = baseService.sumByApplyTime(params).getRecords();
        list.forEach(it->{
            it.setReceiveTimeText(ObjectUtils.isEmpty(it.getReceiveTime())?null:df.format(it.getReceiveTime()));
            it.setTestCompleteTimeText(ObjectUtils.isEmpty(it.getTestCompleteTime())?null:df.format(it.getTestCompleteTime()));
        });
        ExcelUtil.fillExcel(list,"sum_by_apply_time","sum_by_apply_time.xlsx");
    }

    @ApiOperation(value="导出测试申请统计-按状态")
    @PostMapping(value = "/exportSumByStatus")
    public void exportSumByStatus(@RequestBody(required = false) ApplyNoteParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        ApplyNoteSumByStatusVo sum = baseService.sumByStatus(params);
        baseService.writeExcel(sum);
    }
}
