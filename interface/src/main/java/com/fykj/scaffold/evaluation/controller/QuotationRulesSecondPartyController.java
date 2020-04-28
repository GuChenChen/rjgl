package com.fykj.scaffold.evaluation.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesSecondParty;
import com.fykj.scaffold.evaluation.service.impl.QuotationRulesSecondPartyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

import java.util.List;

/**
 * <p>
 *  报价规则乙方前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/quotationRulesSecondParty")
@Api(tags = "乙方报价规则管理接口")
public class QuotationRulesSecondPartyController extends BaseController<QuotationRulesSecondPartyServiceImpl, QuotationRulesSecondParty,BaseParams> {

    @ApiOperation(value = "获取功能测试/安全测试 列表信息")
    @GetMapping(value = "findBySecondParty")
    public JsonResult<List<QuotationRulesSecondParty>> findBySecondParty(String type) {
        List<QuotationRulesSecondParty> list = baseService.findBySecondParty(type);
        return new JsonResult<>(list);
    }

    @ApiOperation(value = "获取性能测试信息")
    @GetMapping(value = "getSecondPerformance")
    public JsonResult<QuotationRulesSecondParty> getSecondPerformance(String type) {
        return new JsonResult<>(baseService.findBySecondPerformance(type));
    }

}
