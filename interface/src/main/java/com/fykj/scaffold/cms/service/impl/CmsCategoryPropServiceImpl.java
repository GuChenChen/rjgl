package com.fykj.scaffold.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryProp;
import com.fykj.scaffold.cms.domain.params.CmsCategoryPropParams;
import com.fykj.scaffold.cms.mapper.CmsCategoryPropMapper;
import com.fykj.scaffold.cms.service.ICmsCategoryPropService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-11-08
 */
@Service
public class CmsCategoryPropServiceImpl extends BaseServiceImpl<CmsCategoryPropMapper, CmsCategoryProp> implements ICmsCategoryPropService {

    @Override
    public IPage<CmsCategoryProp> getListWithQuery(IPage<CmsCategoryProp> page, CmsCategoryPropParams propParams) {
        return baseMapper.getListWithQuery(page,propParams);
    }

    @Override
    public List<CmsCategoryProp> getCategoryPropListByCategoryId(String categoryId) {
       List<CmsCategoryProp> categoryProps = baseMapper.getList(categoryId);
       return categoryProps;
    }

    @Override
    public boolean checkExistCategoryIdAndPropId(String categoryId, String propId) {
        return lambdaQuery().eq(CmsCategoryProp::getCategoryId,categoryId)
                .eq(CmsCategoryProp::getPropId,propId).count()>0;
    }
}
