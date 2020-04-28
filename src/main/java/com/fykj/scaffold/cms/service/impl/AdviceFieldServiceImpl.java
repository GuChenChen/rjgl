package com.fykj.scaffold.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.Advice;
import com.fykj.scaffold.cms.domain.entity.AdviceField;
import com.fykj.scaffold.cms.domain.entity.FieldOption;
import com.fykj.scaffold.cms.domain.entity.FieldType;
import com.fykj.scaffold.cms.mapper.AdviceFieldMapper;
import com.fykj.scaffold.cms.service.IAdviceFieldService;
import com.fykj.scaffold.cms.service.IAdviceService;
import com.fykj.scaffold.cms.service.IFieldOptionService;
import com.fykj.scaffold.cms.service.IFieldTypeService;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import result.ResultCode;
import utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 咨询建议字段服务实现类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdviceFieldServiceImpl extends BaseServiceImpl<AdviceFieldMapper, AdviceField> implements IAdviceFieldService {
    @Autowired
    private IAdviceService adviceService;

    @Autowired
    private IFieldOptionService optionService;

    @Autowired
    private IFieldTypeService typeService;

    @Override
    public boolean save(AdviceField entity) {
        Advice advice = adviceService.getById(entity.getAdviceId());
        entity.setAdviceTitle(advice.getTitle());
        boolean result = super.save(entity);

        if (result && Boolean.TRUE.equals(entity.getOptions())) {
            List<FieldOption> options = entity.getOptionList();
            if (CollectionUtils.isEmpty(options)) {
                throw new BusinessException(ResultCode.NOT_VALID, "请配置选择项。");
            }
            options.forEach(it -> it.setFieldId(entity.getId()));
            result = optionService.saveBatch(options);
        }

        return result;
    }

    @Override
    public boolean updateById(AdviceField entity) {
        FieldType type = typeService.getByCode(entity.getType());
        entity.setOptions(type.getOptions());
        if (!super.updateById(entity)) {
            return false;
        }

        if (Boolean.FALSE.equals(entity.getOptions())) {
            optionService.removeByFieldId(entity.getId());
            return true;
        }

        List<FieldOption> options = entity.getOptionList();
        if (CollectionUtils.isEmpty(options)) {
            return true;
        }

        optionService.removeByFieldId(entity.getId());
        options.forEach(it -> it.setFieldId(entity.getId()));
        return optionService.saveBatch(options);
    }

    @Override
    public IPage<AdviceField> page(BaseParams params) {
        IPage<AdviceField> fields = super.page(params);
        return fields.convert(this::convert);
    }

    @Override
    public AdviceField getById(Serializable id) {
        AdviceField item = super.getById(id);
        item.setOptionList(optionService.findByFieldId(id));
        return item;
    }

    private AdviceField convert(AdviceField field) {
        String typeName = typeService.getNameByCode(field.getType());
        field.setTypeName(typeName);
        return field;
    }

    @SuppressWarnings("unchecked")
    public List<AdviceField> list(String name) {
        return lambdaQuery().like(StringUtil.isNotEmpty(name), AdviceField::getName, name)
                .orderByAsc(AdviceField::getSequence)
                .list();
    }

    @Override
    public List<String> findIdByAdviceId(Serializable adviceId) {
        return findByAdviceId(adviceId).stream()
                .map(AdviceField::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdviceField> findByAdviceId(Serializable adviceId) {
        List<AdviceField> itemList = lambdaQuery().eq(AdviceField::getAdviceId, adviceId)
                .orderByAsc(AdviceField::getSequence)
                .list();

        if (CollectionUtils.isEmpty(itemList)) {
            return Collections.emptyList();
        }
        itemList.stream().filter(AdviceField::getOptions)
                .forEach(it -> it.setOptionList(optionService.findByFieldId(it.getId())));
        return itemList;
    }

    @Override
    public List<AdviceField> findByAdviceCode(String code) {
        Advice advice = adviceService.getByCode(code);
        if (advice == null) {
            throw new BusinessException(ResultCode.FAIL);
        }
        return findByAdviceId(advice.getId());
    }

    @Override
    public List<IdTextVo> getByAdviceId(String adviceId) {
        List<AdviceField> list = lambdaQuery().eq(AdviceField::getAdviceId, adviceId)
                .eq(AdviceField::getRequired,true)
                .list();
        List<IdTextVo> idTextVos = new ArrayList<>();
        list.forEach(it -> idTextVos.add(new IdTextVo(it.getId(),it.getName())));
        return idTextVos;
    }

}
