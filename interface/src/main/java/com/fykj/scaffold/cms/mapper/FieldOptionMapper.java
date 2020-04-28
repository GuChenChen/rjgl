package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.cms.domain.entity.FieldOption;

/**
 * <p>
 * 字段选项Mapper 接口
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
public interface FieldOptionMapper extends BaseMapper<FieldOption> {

    /**
     * 删除指定字段的全部选项
     *
     * @param fieldId 字段主键
     */
    void removeByFieldId(String fieldId);
}
