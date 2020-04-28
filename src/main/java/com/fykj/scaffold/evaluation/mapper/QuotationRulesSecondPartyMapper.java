package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRulesSecondParty;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  报价规则乙方Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface QuotationRulesSecondPartyMapper extends BaseMapper<QuotationRulesSecondParty> {


    /**
     * 获取报价规则
     * @param type
     * @param num
     * @return
     */
    QuotationRulesSecondParty findByTypeAndNum(@Param("type")String type,@Param("num")String num);
}
