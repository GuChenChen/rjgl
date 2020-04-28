package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsExtendedProp;
import com.fykj.scaffold.cms.mapper.CmsExtendedPropMapper;
import com.fykj.scaffold.cms.service.ICmsExtendedPropService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-11-06
 */
@Service
public class CmsExtendedPropServiceImpl extends BaseServiceImpl<CmsExtendedPropMapper, CmsExtendedProp> implements ICmsExtendedPropService {

    @Override
    public List<CmsExtendedProp> getExtendedPropList() {
        return lambdaQuery().eq(CmsExtendedProp::getStatus,true).list();
    }
}
