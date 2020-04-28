package com.fykj.scaffold.evaluation.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesFirstParty;
import com.fykj.scaffold.evaluation.service.impl.QuotationRulesFirstPartyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;

/**
 * <p>
 *  报价规则甲方前端控制器
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/admin/quotationRulesFirstParty")
@Api(tags = "甲方报价规则管理接口")
public class QuotationRulesFirstPartyController extends BaseController<QuotationRulesFirstPartyServiceImpl, QuotationRulesFirstParty,BaseParams> {

    @ApiOperation(value = "获取甲方报价规则信息")
    @GetMapping(value = "getFirstDetail")
    public JsonResult<QuotationRulesFirstParty> publicDetail() {
        return new JsonResult<>(baseService.findByFirst());
    }
}
