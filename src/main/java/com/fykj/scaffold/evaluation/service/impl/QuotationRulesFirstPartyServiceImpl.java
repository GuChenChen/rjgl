package com.fykj.scaffold.evaluation.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesFirstParty;
import com.fykj.scaffold.evaluation.mapper.QuotationRulesFirstPartyMapper;
import com.fykj.scaffold.evaluation.service.IQuotationRulesFirstPartyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  报价规则甲方服务实现类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@Service
public class QuotationRulesFirstPartyServiceImpl extends BaseServiceImpl<QuotationRulesFirstPartyMapper, QuotationRulesFirstParty> implements IQuotationRulesFirstPartyService {

    @Override
    public QuotationRulesFirstParty findByFirst(){
        return lambdaQuery().one();
    }
}
