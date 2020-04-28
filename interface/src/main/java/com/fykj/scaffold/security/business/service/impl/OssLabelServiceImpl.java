package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.OssLabel;
import com.fykj.scaffold.security.business.mapper.OssLabelMapper;
import com.fykj.scaffold.security.business.service.IOssLabelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-11-13
 */
@Service
public class OssLabelServiceImpl extends BaseServiceImpl<OssLabelMapper, OssLabel> implements IOssLabelService {

    @Override
    public List<String> getByOssId(String ossId) {
        List<OssLabel> ossLabels = lambdaQuery().eq(OssLabel::getOssId,ossId).list();
        List<String> labels = new ArrayList<>();
        ossLabels.forEach(it -> labels.add(it.getLabelId()));
        return labels;
    }
}
