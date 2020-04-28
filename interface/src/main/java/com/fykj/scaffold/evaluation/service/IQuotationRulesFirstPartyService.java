package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesFirstParty;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IQuotationRulesFirstPartyService extends IService<QuotationRulesFirstParty> {


    /**
     * 获取甲方报价规则信息
     * @return
     */
    QuotationRulesFirstParty findByFirst();
}
