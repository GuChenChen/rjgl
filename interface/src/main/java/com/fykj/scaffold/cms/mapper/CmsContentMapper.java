package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.cms.domain.entity.CmsContent;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangm
 * @since 2019-10-26
 */
public interface CmsContentMapper extends BaseMapper<CmsContent> {

    /**
     * 通过id更新实际阅读量
     * @param id
     */
    void updateActualReadingById(String id);
}
