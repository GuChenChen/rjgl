package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.cms.domain.dto.CascaderDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategory;
import com.fykj.scaffold.cms.domain.entity.CmsReadingRecord;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangx
 * @since 2019-11-22
 */
public interface CmsReadingRecordMapper extends BaseMapper<CmsReadingRecord> {

    /**
     * 通过内容id查询该内容阅读记录数
     * @param id
     * @return
     */
    Integer countReadingRecordById(String id);
}
