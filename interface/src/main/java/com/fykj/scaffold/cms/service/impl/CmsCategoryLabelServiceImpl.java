package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryLabel;
import com.fykj.scaffold.cms.mapper.CmsCategoryLabelMapper;
import com.fykj.scaffold.cms.service.ICmsCategoryLabelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-11-04
 */
@Service
public class CmsCategoryLabelServiceImpl extends BaseServiceImpl<CmsCategoryLabelMapper, CmsCategoryLabel> implements ICmsCategoryLabelService {

    @Override
    public boolean deleteByCategoryId(String categoryId) {
        return lambdaUpdate().eq(CmsCategoryLabel::getCategoryId, categoryId).remove();
    }

    @Override
    public List<String> getByCategoryId(String categoryId) {
        List<CmsCategoryLabel> cmsCategoryLabels = lambdaQuery().eq(CmsCategoryLabel::getCategoryId,categoryId).list();
        List<String> labels = new ArrayList<>();
        cmsCategoryLabels.forEach(it -> labels.add(it.getLabelId()));
        return labels;
    }
}
