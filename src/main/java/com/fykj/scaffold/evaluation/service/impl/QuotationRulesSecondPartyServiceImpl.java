package com.fykj.scaffold.evaluation.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesSecondParty;
import com.fykj.scaffold.evaluation.mapper.QuotationRulesSecondPartyMapper;
import com.fykj.scaffold.evaluation.service.IQuotationRulesSecondPartyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  报价规则乙方服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class QuotationRulesSecondPartyServiceImpl extends BaseServiceImpl<QuotationRulesSecondPartyMapper, QuotationRulesSecondParty> implements IQuotationRulesSecondPartyService {

    @Override
    public List<QuotationRulesSecondParty> findBySecondParty(String type){
        return lambdaQuery().eq(QuotationRulesSecondParty::getType,type).orderByAsc(QuotationRulesSecondParty::getStartNum).list();
    }

    @Override
    public QuotationRulesSecondParty findBySecondPerformance(String type){
        List<QuotationRulesSecondParty> list = findBySecondParty(type);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public QuotationRulesSecondParty findByTypeAndNum(String type, String num) {
        QuotationRulesSecondParty byTypeAndNum = baseMapper.findByTypeAndNum(type, num);
        return byTypeAndNum;
    }

}
