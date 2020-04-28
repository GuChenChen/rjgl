package com.fykj.scaffold.security.business.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.OssConfig;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangf
 * @since 2019-10-21
 */
public interface IOssConfigService extends IService<OssConfig> {

    /**
     * 获取oss配置
     *
     * @return 配置
     */
    OssConfig getConfig();
}
