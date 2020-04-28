package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesSecondParty;

import java.util.List;

/**
 * <p>
 *  报价规则乙方服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IQuotationRulesSecondPartyService extends IService<QuotationRulesSecondParty> {

    /**
     * 获取功能列表信息
     * @param type 分类
     * @return
     */
    List<QuotationRulesSecondParty> findBySecondParty(String type);

    /**
     * 获取性能测试信息
     * @param type
     * @return
     */
    QuotationRulesSecondParty findBySecondPerformance(String type);

    QuotationRulesSecondParty findByTypeAndNum(String type,String num);

}
