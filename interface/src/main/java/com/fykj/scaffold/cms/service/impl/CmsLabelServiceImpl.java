package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsLabel;
import com.fykj.scaffold.cms.mapper.CmsLabelMapper;
import com.fykj.scaffold.cms.service.ICmsLabelService;
import org.springframework.stereotype.Service;

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
public class CmsLabelServiceImpl extends BaseServiceImpl<CmsLabelMapper, CmsLabel> implements ICmsLabelService {

    @Override
    public List<CmsLabel> getLabelList() {
        return lambdaQuery().eq(CmsLabel::getStatus,true).list();
    }
}
