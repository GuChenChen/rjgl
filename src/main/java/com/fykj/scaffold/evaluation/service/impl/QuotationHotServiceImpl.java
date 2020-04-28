package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.QuotationHot;
import com.fykj.scaffold.evaluation.domain.params.PartyBInputParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationHotParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationHotVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.mapper.QuotationHotMapper;
import com.fykj.scaffold.evaluation.service.IQuotationHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.StringUtil;
import java.time.LocalDate;


/**
 * 
 *
 * 服务实现类
 * @author wangming
 * @email ${email}
 * @date 2020-04-16 13:39:06
 */
@Service
public class QuotationHotServiceImpl extends BaseServiceImpl<QuotationHotMapper, QuotationHot> implements IQuotationHotService {

    @Autowired
    ReportPageViewServiceImpl reportPageViewService;

    @Override
    public IPage<QuotationHotVo> findByViewTime(QuotationRecordViewParams params) {
        Page<QuotationRecordViewVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        if ( !StringUtils.isEmpty(params.getViewTimeStart()) && !StringUtils.isEmpty(params.getViewTimeEnd())){
            params.setViewTimeEnd(reportPageViewService.getViewTimeEnd(params.getViewTimeEnd()));
        }
        IPage<QuotationHotVo> byViewTime = baseMapper.findByViewTime(page,params);
        return byViewTime;
    }

    @Override
    public QuotationHotVo findByCurrentMonth() {
        QuotationHotVo quotationHotVo = baseMapper.findByCurrentMonth();
        return quotationHotVo;
    }

    @Override
    public Boolean updateQuotationHotById(QuotationHotParams params) {
        return baseMapper.updateQuotationHotById(params);
    }

    @Override
    public Boolean updateQuotation(String type, PartyBInputParams params) {
        QuotationHotVo quotationHotVo = baseMapper.findByCurrentMonth();
        if (StringUtil.isNotEmpty(quotationHotVo)) {
            QuotationHotParams quotationHotParams = new QuotationHotParams();
            quotationHotParams.setId(quotationHotVo.getId());
            if (type=="firstParty") {
                if (quotationHotVo.getFirstParty()==null) {
                    quotationHotParams.setFirstParty(1);
                }else {
                    quotationHotParams.setFirstParty(quotationHotVo.getFirstParty() + 1);
                }
            }else if(type=="secondParty"){
                if (quotationHotVo.getSecondParty()==null) {
                    quotationHotParams.setSecondParty(1);
                }else {
                    quotationHotParams.setSecondParty(quotationHotVo.getSecondParty()+1);
                }
                if (quotationHotVo.getFunctionTest()==null&&(params.getSubsystemCount()!=null || params.getSubsystemModuleCount()!=null || params.getModuleFunctionCount()!=null)) {
                    quotationHotParams.setFunctionTest(1);
                }else if (quotationHotVo.getFunctionTest()!=null&&(params.getSubsystemCount()!=null || params.getSubsystemModuleCount()!=null || params.getModuleFunctionCount()!=null)){
                    quotationHotParams.setFunctionTest(quotationHotVo.getFunctionTest()+1);
                }
                if (quotationHotVo.getPerformanceTest()==null&&(params.getConcurrentCount()!=null || params.getPerformanceCount()!=null)) {
                    quotationHotParams.setPerformanceTest(1);
                }else if (quotationHotVo.getPerformanceTest()!=null&&(params.getConcurrentCount()!=null || params.getPerformanceCount()!=null)){
                    quotationHotParams.setPerformanceTest(quotationHotVo.getPerformanceTest()+1);
                }
                if (quotationHotVo.getSecurityTestCode()==null&&(params.getCodeLinesCount()!=null || params.getSystemCount()!=null)) {
                    quotationHotParams.setSecurityTestCode(1);
                }else if (quotationHotVo.getSecurityTestCode()!=null&&(params.getCodeLinesCount()!=null || params.getSystemCount()!=null)){
                    quotationHotParams.setSecurityTestCode(quotationHotVo.getSecurityTestCode()+1);
                }
            }
            return baseMapper.updateQuotationHotById(quotationHotParams);
        }else {
            QuotationHot quotationHot = new QuotationHot();
            if (type=="firstParty") {
                quotationHot.setFirstParty(1);
                quotationHot.setViewTime(LocalDate.now());
            }else if(type=="secondParty"){
                quotationHot.setSecondParty(1);
                if (params.getSubsystemCount()!=null || params.getSubsystemModuleCount()!=null || params.getModuleFunctionCount()!=null) {
                    quotationHot.setFunctionTest(1);
                }
                if (params.getConcurrentCount()!=null || params.getPerformanceCount()!=null) {
                    quotationHot.setPerformanceTest(1);
                }
                if ((params.getCodeLinesCount()!=null || params.getSystemCount()!=null)) {
                    quotationHot.setSecurityTestCode(1);
                }
                quotationHot.setViewTime(LocalDate.now());
            }
            return addQuotationHot(quotationHot);
        }
    }

    private Boolean addQuotationHot(QuotationHot quotationHot){
        return super.save(quotationHot);
    }

}