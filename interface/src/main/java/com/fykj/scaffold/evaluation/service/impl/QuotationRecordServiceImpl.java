package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRecord;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesFirstParty;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesSecondParty;
import com.fykj.scaffold.evaluation.domain.params.*;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationWardVo;
import com.fykj.scaffold.evaluation.mapper.QuotationRecordMapper;
import com.fykj.scaffold.evaluation.service.*;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import result.ResultCode;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  获取报价记录服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class QuotationRecordServiceImpl extends BaseServiceImpl<QuotationRecordMapper, QuotationRecord> implements IQuotationRecordService {


    @Autowired
    ReportPageViewServiceImpl reportPageViewService;

    @Autowired
    IQuotationRulesFirstPartyService quotationRulesFirstPartyService;

    @Autowired
    IQuotationRulesSecondPartyService quotationRulesSecondPartyService;

    @Autowired
    IQuotationHotService quotationHotService;

    @Autowired
    private ISysEvaluationUserService sysEvaluationUserService;

    @Override
    public IPage<QuotationRecordViewVo> getMapWithQuery(QuotationRecordViewParams params) {
        Page<QuotationRecordViewVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        if ( !StringUtils.isEmpty(params.getViewTimeStart()) && !StringUtils.isEmpty(params.getViewTimeEnd())){
            params.setViewTimeEnd(reportPageViewService.getViewTimeEnd(params.getViewTimeEnd()));
        }
        IPage<QuotationRecordViewVo> result = baseMapper.getMapWithQuery(page, params);
        return result;
    }

    @Override
    public IPage<QuotationRecordVo> getListWithQuery(QuotationRecordParams params) {
        Page<QuotationRecordVo> page = new Page<>(params.getCurrentPage(), params.getPageSize());
        if ( !StringUtils.isEmpty(params.getViewTimeStart()) && !StringUtils.isEmpty(params.getViewTimeEnd())){
            params.setViewTimeEnd(this.getViewTimeEnd(params.getViewTimeEnd()));
        }
        IPage<QuotationRecordVo> result = baseMapper.getListWithQuery(page, params);
        return result;
    }

    @Override
    public QuotationWardVo partyAWard(PartyAInputPriseParam param) {
        /*更新报价热度表*/
        quotationHotService.updateQuotation("firstParty",null);
        Pattern pattern = getCheckRule("^(([1-9]\\d*)(\\.\\d{1,2})?|0\\.([1-9]|\\d[1-9])|0)$");
        Matcher matcher = pattern.matcher(param.getInputPrise());
        if (!matcher.matches()|| param.getInputPrise().equals("0")) {
            throw new BusinessException(ResultCode.FAIL,"请输入正确金额不并不得小于小数点后两位");
        }
        BigDecimal inputPrise=new BigDecimal(param.getInputPrise());
        QuotationWardVo quotationWardVo = new QuotationWardVo();
        QuotationRecord quotationRecord = new QuotationRecord();
        QuotationRulesFirstParty quotationRulesFirstParty = quotationRulesFirstPartyService.findByFirst();
        BigDecimal amountPercent = quotationRulesFirstParty.getAmountPercent();
        BigDecimal floatPercent = quotationRulesFirstParty.getFloatPercent();
//        算法
        BigDecimal  rate= inputPrise.multiply(amountPercent);
        BigDecimal upWard = rate.add(rate.multiply(floatPercent));
        BigDecimal downWard =rate.subtract(rate.multiply(floatPercent));
        quotationWardVo.setUpWard(upWard);
        quotationWardVo.setDownWard(downWard);
        quotationRecord.setType("firstParty");
        quotationRecord.setInputPrise(inputPrise);
        quotationRecord.setUpWard(upWard);
        quotationRecord.setDownWard(downWard);
//        quotationRecord.setContact();
//        quotationRecord.setEvaluationUserId(sysEvaluationUserService.getByLoginUser().getId());
        /*保存到报价记录表*/
        saveQuotationRecord(quotationRecord);
        return quotationWardVo;
    }

    @Override
    public QuotationWardVo partyBWard(PartyBInputParams params) {
        /*更新报价热度表*/
        quotationHotService.updateQuotation("secondParty",params);
        Pattern pattern = getCheckRule("^[1-9]\\d*$");
        boolean codeLinesCountMaster = pattern.matcher(params.getCodeLinesCount()).matches();
        boolean concurrentCountMaster  = pattern.matcher(params.getConcurrentCount()).matches();
        boolean performanceCountMaster  = pattern.matcher(params.getPerformanceCount()).matches();
        boolean subsystemCountMaster  = pattern.matcher(params.getSubsystemCount()).matches();
        boolean moduleFunctionCountMaster  = pattern.matcher(params.getModuleFunctionCount()).matches();
        boolean subsystemModuleCountMaster  = pattern.matcher(params.getSubsystemModuleCount()).matches();
        boolean systemCountMaster  = pattern.matcher(params.getSystemCount()).matches();
        if (!codeLinesCountMaster  || !concurrentCountMaster  || !performanceCountMaster || !subsystemModuleCountMaster  || !systemCountMaster || !subsystemCountMaster  || !moduleFunctionCountMaster) {
            throw new BusinessException(ResultCode.FAIL,"请输入非0正整数");
        }
        String functionTest = "functionTest";//功能点数
        String securityTestCode ="securityTestCode";//代码行数
        String securityTestSys ="securityTestSys";//系统数
        String performanceTest ="performanceTest";//性能测试
        QuotationRulesSecondParty functionPrice = quotationRulesSecondPartyService.findByTypeAndNum(functionTest, params.getModuleFunctionCount());
        QuotationRulesSecondParty CodeLinesCountPrice = quotationRulesSecondPartyService.findByTypeAndNum(securityTestCode, params.getCodeLinesCount());
        QuotationRulesSecondParty systemPrice = quotationRulesSecondPartyService.findByTypeAndNum(securityTestSys, params.getSystemCount());
        QuotationRulesSecondParty pPrice = quotationRulesSecondPartyService.findByTypeAndNum(performanceTest, null);
        BigDecimal ssc = BigDecimal.valueOf(Long.parseLong(params.getSubsystemCount()));
        BigDecimal smc = BigDecimal.valueOf(Long.parseLong(params.getSubsystemModuleCount()));
        BigDecimal mfc = BigDecimal.valueOf(Long.parseLong(params.getModuleFunctionCount()));
        BigDecimal pfc = BigDecimal.valueOf(Long.parseLong(params.getPerformanceCount()));
        BigDecimal clc = BigDecimal.valueOf(Long.parseLong(params.getCodeLinesCount()));
        BigDecimal sc = BigDecimal.valueOf(Long.parseLong(params.getSystemCount()));
//        算法
//        功能测试的子系统数*平均每个子系统下模块数*平均每个模块功能点数*对应区间价格+
//        性能测试的性能点数*对应价格+参数价格+
//        安全测试的代码行数对应价格+
//        系统数*对应区间价格
        BigDecimal million = new BigDecimal("10000");
        BigDecimal f = ssc.multiply(smc.divide(ssc,2, BigDecimal.ROUND_HALF_UP)).multiply(mfc.divide(smc,2, BigDecimal.ROUND_HALF_UP)).multiply(functionPrice.getUnitPrise()).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal p = pfc.multiply(pPrice.getUnitPrise()).setScale(2, BigDecimal.ROUND_HALF_UP).add(pPrice.getFixedPrise());
        BigDecimal s = CodeLinesCountPrice.getUnitPrise();
        BigDecimal i = sc.multiply(systemPrice.getUnitPrise()).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal price = f.add(p).add(s).add(i);
        BigDecimal downWard = price.subtract(price.multiply(pPrice.getFloatPercent())).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal upWard = price.add(price.multiply(pPrice.getFloatPercent())).setScale(2, BigDecimal.ROUND_HALF_UP);
//        除万
        BigDecimal downWardW = downWard.divide(million, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal upWardW = upWard.divide(million, 2, BigDecimal.ROUND_HALF_UP);
        QuotationWardVo quotationWardVo = new QuotationWardVo();
        quotationWardVo.setDownWard(downWardW);
        quotationWardVo.setUpWard(upWardW);
        QuotationRecord quotationRecord = new QuotationRecord();
        quotationRecord.setType("secondParty");
        quotationRecord.setSubsystemCount(Integer.valueOf(params.getSubsystemCount()));
        quotationRecord.setSubsystemModuleCount(Integer.valueOf(params.getSubsystemModuleCount()));
        quotationRecord.setModuleFunctionCount(Integer.valueOf(params.getModuleFunctionCount()));
        quotationRecord.setConcurrentCount(Integer.valueOf(params.getConcurrentCount()));
        quotationRecord.setPerformanceCount(Integer.valueOf(params.getPerformanceCount()));
        quotationRecord.setCodeLinesCount(Integer.valueOf(params.getCodeLinesCount()));
        quotationRecord.setSystemCount(Integer.valueOf(params.getSystemCount()));
        quotationRecord.setDownWard(downWardW);
        quotationRecord.setUpWard(upWardW);
//        quotationRecord.setContact();
//        quotationRecord.setEvaluationUserId(sysEvaluationUserService.getByLoginUser().getId());
        /*保存到报价记录表*/
        saveQuotationRecord(quotationRecord);
        return quotationWardVo;
    }

    /**
     * @Description: 根据前台传来的时间将结束时间加一个天
     * @Param: [viewTimeEnd]
     * @return: java.lang.String
     * @Author: Mr.Wei
     * @Date: 2020-4-8
     */
    public String getViewTimeEnd(String viewTimeEnd){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if ( !StringUtils.isEmpty(viewTimeEnd)){
            try {
                Date dd = df.parse(viewTimeEnd);
                calendar.setTime(dd);
                calendar.add(Calendar.DATE, 1);//加一天
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return df.format(calendar.getTime());
    }

    private Pattern getCheckRule(String str){
        Pattern pattern = Pattern.compile(str);
        return pattern;
    }

     public void saveQuotationRecord(QuotationRecord quotationRecord){
       super.save(quotationRecord);
    }
}
