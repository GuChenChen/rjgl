package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.QuotationHot;
import com.fykj.scaffold.evaluation.domain.params.PartyBInputParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationHotParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationHotVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * 服务类
 * @author wangming
 * @email ${email}
 * @date 2020-04-16 13:39:06
 */
public interface IQuotationHotService extends IService<QuotationHot> {

    /**
     * 按月份区间获取报价热度
     *
     * @param params
     *
     * @return
     */
    IPage<QuotationHotVo> findByViewTime(QuotationRecordViewParams params);

    /**
     * 查询当前月份报价热度信息
     * @return QuotationHotVo
     */
    QuotationHotVo findByCurrentMonth();

    /**
     * 根据id更新当前月份报价热度信息
     *
     * QuotationHotParams params 更新参数
     * @return null
     */
    Boolean updateQuotationHotById(@Param("params")QuotationHotParams params);

    /**
     * 更新报价热度表
     *String type 登录客户类型（甲乙）
     *,PartyBInputParams params 统计参数
     * @return null
     */
    Boolean updateQuotation(String type,PartyBInputParams params);
}

