package com.fykj.scaffold.evaluation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.entity.QuotationHot;
import com.fykj.scaffold.evaluation.domain.params.QuotationHotParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationHotVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.service.impl.QuotationHotServiceImpl;
import com.fykj.scaffold.support.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * 
 *
 * 获取报价热度
 * @author gcc
 * @email ${email}
 * @date 2020-04-16 13:39:06
 */
@RestController
@RequestMapping("/admin/quotationHot")
@Api(tags = "获取报价热度接口")
public class QuotationHotController extends BaseController<QuotationHotServiceImpl, QuotationHot, QuotationHotParams> {

    @ApiOperation("报价热度统计")
    @PostMapping(value = "/page")
    public JsonResult<IPage<QuotationHotVo>> findByPage(@RequestBody(required = false) QuotationRecordViewParams params) {
        if (params == null) {
            params = new QuotationRecordViewParams();
        }
        //查询列表数据
        IPage<QuotationHotVo> list = baseService.findByViewTime(params);
        return new JsonResult<>(list);
    }

    @ApiOperation(value="报价热度导出列表")
    @PostMapping(value = "/exportExcel")
    public void exportExcel(@RequestBody(required = false) QuotationRecordViewParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<QuotationHotVo> list = baseService.findByViewTime(params).getRecords();
        ExcelUtil.fillExcel(list,"quotation_record_view","quotation_record_view.xlsx");
    }
}
