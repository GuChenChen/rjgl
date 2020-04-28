package com.fykj.scaffold.api;


import com.fykj.scaffold.evaluation.domain.params.PartyAInputPriseParam;
import com.fykj.scaffold.evaluation.domain.params.PartyBInputParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationWardVo;
import com.fykj.scaffold.evaluation.service.IQuotationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;


/**
 * <p>
 *  获取报价记录前端控制器
 * </p>
 *
 * @author gcc
 * @since 2020-04-09
 */

@RestController
@RequestMapping("/api/quotationRecord")
@Slf4j
@Api(tags = "前端获取报价接口")
public class ApiQuotationRecordController {

    @Autowired
    private IQuotationRecordService baseService;

    @ApiOperation("根据甲方输入金额获取报价")
    @PostMapping(value = "/getOfferA")
    public JsonResult<QuotationWardVo> getOfferA(@RequestBody(required = false) PartyAInputPriseParam params) {
        if (params == null) {
            params = new PartyAInputPriseParam();
        }
        //查询列表数据
        QuotationWardVo quotationWardVo = baseService.partyAWard(params);
        return new JsonResult<>(quotationWardVo);
    }

    @ApiOperation("根据乙方输入金额获取报价")
    @PostMapping(value = "/getOfferB")
    public JsonResult<QuotationWardVo> getOfferB(@RequestBody(required = false) PartyBInputParams params) {
        if (params == null) {
            params = new PartyBInputParams();
        }
        //查询列表数据
        QuotationWardVo quotationWardVo = baseService.partyBWard(params);
        return new JsonResult<>(quotationWardVo);
    }
}

