package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.FieldOption;
import com.fykj.scaffold.cms.mapper.FieldOptionMapper;
import com.fykj.scaffold.cms.service.IFieldOptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字段选项服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FieldOptionServiceImpl extends BaseServiceImpl<FieldOptionMapper, FieldOption> implements IFieldOptionService {

    @Override
    public void removeByFieldId(String fieldId) {
        baseMapper.removeByFieldId(fieldId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FieldOption> findByFieldId(Serializable fieldId) {
        return lambdaQuery().eq(FieldOption::getFieldId, fieldId)
                .orderByAsc(FieldOption::getSequence).list();
    }

}
