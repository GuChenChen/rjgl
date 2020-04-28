package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.QuotationHot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.evaluation.domain.params.QuotationHotParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationHotVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *
 * Mapper 接口
 * @author wangming
 * @email ${email}
 * @date 2020-04-16 13:39:06
 */
public interface QuotationHotMapper extends BaseMapper<QuotationHot> {


    /**
     * 按月份区间分页查询报价热度信息
     * @param params 参数
     * @return 分页结果
     */
    IPage<QuotationHotVo> findByViewTime(IPage<QuotationRecordViewVo> page,@Param("params") QuotationRecordViewParams params);

    /**
     * 查询当前月份报价热度信息
     *
     * @return QuotationHotVo
     */
    QuotationHotVo findByCurrentMonth();

    Boolean updateQuotationHotById(@Param("params")QuotationHotParams params);
}
