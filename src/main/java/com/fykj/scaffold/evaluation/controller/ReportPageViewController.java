package com.fykj.scaffold.evaluation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.ReportPageView;
import com.fykj.scaffold.evaluation.domain.params.ReportPageParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPageViewParams;
import com.fykj.scaffold.evaluation.domain.params.ReportPagesParams;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageViewVo;
import com.fykj.scaffold.evaluation.domain.vo.ReportPageVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchOneVo;
import com.fykj.scaffold.evaluation.domain.vo.WorkBenchVo;
import com.fykj.scaffold.evaluation.service.impl.ReportPageViewServiceImpl;
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
 * <p>
 *  前端各模块浏览量统计前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/reportPageView")
@Api(tags = "模块使用记录管理接口")
public class ReportPageViewController extends BaseController<ReportPageViewServiceImpl, ReportPageView, BaseParams> {

//    @SysLogMethod("新增")
    @ApiOperation("根据code存储前端各模块浏览量")
    @PostMapping(value = "/saveCode")
    public void saveCode(@RequestBody String code){
        baseService.saveReportPageByCode(code);
    }

//    @SysLogMethod("新增")
    @ApiOperation("获取报价存入浏览数据")
    @PostMapping(value = "/saveFirstDetail")
    public void saveFirstDetail(@RequestBody String content){
        baseService.saveFirstDetail(content);
    }

//    @SysLogMethod("查询")
    @ApiOperation("前端各模块浏览量统计分页查询列表")
    @PostMapping(value = "/page")
    public JsonResult<IPage<ReportPageVo>> page(@RequestBody(required = false) ReportPageViewParams params) {
        if (params == null) {
            params = new ReportPageViewParams();
        }
        //查询列表数据
        IPage<ReportPageVo> list = baseService.getMapWithQuery(params);
        return new JsonResult<>(list);
    }

    @ApiOperation(value="前端各模块浏览量统计导出列表")
    @PostMapping(value = "/exportExcel")
    public void exportExcel(@RequestBody(required = false) ReportPageViewParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<ReportPageVo> list = baseService.getMapWithQuery(params).getRecords();
        ExcelUtil.fillExcel(list,"report_page_view","report_page_view.xlsx");
    }


    //    @SysLogMethod("查询")
    @ApiOperation("用户页面使用情况汇总分页查询列表")
    @PostMapping(value = "/pageOne")
    public JsonResult<IPage<ReportPageViewVo>> pageView(@RequestBody(required = false) ReportPageParams params) {
        if (params == null) {
            params = new ReportPageParams();
        }
        //查询列表数据
        IPage<ReportPageViewVo> list = baseService.getListWithQuery(params);
        return new JsonResult<>(list);
    }


    @ApiOperation(value="用户页面使用情况汇总导出列表")
    @PostMapping(value = "/exportExcels")
    public void exportExcel(@RequestBody(required = false) ReportPageParams params) {
        params.setPageSize(Integer.MAX_VALUE);
        params.setCurrentPage(1);
        List<ReportPageViewVo> list = baseService.getListWithQuery(params).getRecords();
        ExcelUtil.fillExcel(list,"report_page","report_page.xlsx");
    }


    //    @SysLogMethod("查询")
    @ApiOperation("测试单位账号管理单位详情")
    @PostMapping(value = "/getListByUserId")
    public JsonResult<IPage<ReportPageViewVo>> getListByUserId(@RequestBody(required = false) ReportPagesParams params) {
        if (params == null) {
            params = new ReportPagesParams();
        }
        //查询列表数据
        IPage<ReportPageViewVo> list = baseService.getListByUserId(params);
        return new JsonResult<>(list);
    }

    //    @SysLogMethod("查询")
    @ApiOperation("工作台今日各模块浏览量")
    @PostMapping(value = "/workBenchPage")
    public JsonResult<List<WorkBenchVo>> workBenchPage() {
        //查询列表数据
        List<WorkBenchVo> list = baseService.getWorkBenchPage();
        return new JsonResult<>(list);
    }

    //    @SysLogMethod("查询")
    @ApiOperation("测试申请数及订单金额对比")
    @PostMapping(value = "/workBenchPageOne")
    public JsonResult<List<WorkBenchOneVo>> getWorkBenchPageOne() {
        //查询列表数据
        List<WorkBenchOneVo> list = baseService.getWorkBenchPageOne();
        return new JsonResult<>(list);
    }

    //    @SysLogMethod("查询")
    @ApiOperation("注册申请数查询")
    @PostMapping(value = "/getWorkBenchPageNum")
    public JsonResult<WorkBenchVo> getWorkBenchPageNum() {
        //查询列表数据
        WorkBenchVo workBenchVo = baseService.getWorkBenchPageNum();
        return new JsonResult<>(workBenchVo);
    }

}
