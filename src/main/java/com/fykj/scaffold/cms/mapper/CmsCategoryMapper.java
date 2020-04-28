package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.cms.domain.dto.CascaderDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategory;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangm
 * @since 2019-10-25
 */
public interface CmsCategoryMapper extends BaseMapper<CmsCategory> {

    List<CascaderDto> findByParent(String parentId);
}
