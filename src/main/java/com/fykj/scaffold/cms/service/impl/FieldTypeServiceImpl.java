package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.FieldType;
import com.fykj.scaffold.cms.mapper.FieldTypeMapper;
import com.fykj.scaffold.cms.service.IFieldTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 字段类型服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FieldTypeServiceImpl extends BaseServiceImpl<FieldTypeMapper, FieldType> implements IFieldTypeService {

    @Override
    public FieldType getByCode(String code) {
        return lambdaQuery().eq(FieldType::getCode, code).one();
    }

    @Override
    public String getNameByCode(String type) {
        FieldType collectType = getByCode(type);
        if (collectType == null) {
            return null;
        }
        return collectType.getName();
    }

}
