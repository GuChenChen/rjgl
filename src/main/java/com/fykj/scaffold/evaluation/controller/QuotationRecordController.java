package com.fykj.scaffold.evaluation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRecord;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordVo;
import com.fykj.scaffold.evaluation.service.IQuotationRecordService;
import com.fykj.scaffold.evaluation.service.impl.QuotationRecordServiceImpl;
import com.fykj.scaffold.support.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * <p>
 *  获取报价记录前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/quotationRecord")
@Api(tags = "获取报价管理接口")
public class QuotationRecordController extends BaseController<QuotationRecordServiceImpl, QuotationRecord,BaseParams> {

    @ApiOperation("报价热度统计")
    @PostMapping(value = "/page")
    public JsonResult<IPage<QuotationRecordViewVo>> findByPage(@RequestBody(required = false) QuotationRecordViewParams params) {
        if (params == null) {
            params = new QuotationRecordViewParams();
        }
        //查询列表数据
        IPage<QuotationRecordViewVo> list = baseService.getMapWithQuery(params);
        return new JsonResult<>(list);
    }

    @ApiOperation(value="报价热度导出列表")
    @PostMapping(value = "/exportExcel")
    public void exportExcel(@RequestBody(required = false) QuotationRecordViewParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<QuotationRecordViewVo> list = baseService.getMapWithQuery(params).getRecords();
        ExcelUtil.fillExcel(list,"quotation_record_view","quotation_record_view.xlsx");
    }

    @ApiOperation("模拟报价记录")
    @PostMapping(value = "/byPage")
    public JsonResult<IPage<QuotationRecordVo>> byPage(@RequestBody(required = false) QuotationRecordParams params) {
        if (params == null) {
            params = new QuotationRecordParams();
        }
        //查询列表数据
        IPage<QuotationRecordVo> list = baseService.getListWithQuery(params);
        return new JsonResult<>(list);
    }

    @ApiOperation(value="模拟报价记录导出列表")
    @PostMapping(value = "/exportExcelRe")
    public void exportExcelRe(@RequestBody(required = false) QuotationRecordParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<QuotationRecordVo> list = baseService.getListWithQuery(params).getRecords();
        ExcelUtil.fillExcel(list,"quotation_record","quotation_record.xlsx");
    }
}
