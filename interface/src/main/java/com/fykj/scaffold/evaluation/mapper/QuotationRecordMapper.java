package com.fykj.scaffold.evaluation.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.QuotationRecord;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordParams;
import com.fykj.scaffold.evaluation.domain.params.QuotationRecordViewParams;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordViewVo;
import com.fykj.scaffold.evaluation.domain.vo.QuotationRecordVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  获取报价记录Mapper 接口
 * </p>
 *
 * @author feihj
 * @since 2020-03-31
 */
@SqlParser(filter=true)
public interface QuotationRecordMapper extends BaseMapper<QuotationRecord> {

    IPage<QuotationRecordViewVo> getMapWithQuery(IPage<QuotationRecordViewVo> page, @Param("params") QuotationRecordViewParams params);

    IPage<QuotationRecordVo> getListWithQuery(IPage<QuotationRecordVo> page, @Param("params") QuotationRecordParams params);
}
