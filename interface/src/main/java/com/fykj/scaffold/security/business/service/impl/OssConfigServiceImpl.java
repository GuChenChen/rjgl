package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.entity.OssConfig;
import com.fykj.scaffold.security.business.mapper.OssConfigMapper;
import com.fykj.scaffold.security.business.service.IOssConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangf
 * @since 2019-10-21
 */
@Service
public class OssConfigServiceImpl extends BaseServiceImpl<OssConfigMapper, OssConfig> implements IOssConfigService {

    @Override
    public OssConfig getConfig() {
        return lambdaQuery().one();
    }
}
