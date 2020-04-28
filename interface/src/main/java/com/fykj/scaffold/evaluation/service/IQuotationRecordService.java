package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRecord;
import com.fykj.scaffold.evaluation.domain.params.PartyAInputPriseParam;
import com.fykj.scaffold.evaluation.domain.params.PartyBInputParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationWardVo;

/**
 * <p>
 *  获取报价记录服务类
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
public interface IQuotationRecordService extends IService<QuotationRecord> {
    IPage<QuotationRecordViewVo> getMapWithQuery(QuotationRecordViewParams params);

    IPage<QuotationRecordVo> getListWithQuery(QuotationRecordParams params);

    QuotationWardVo partyAWard(PartyAInputPriseParam param);

    QuotationWardVo partyBWard(PartyBInputParams param);

    String getViewTimeEnd(String viewTimeEnd);

}
