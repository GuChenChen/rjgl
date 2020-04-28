package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.OssLabel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-11-13
 */
public interface IOssLabelService extends IService<OssLabel> {

    List<String> getByOssId(String ossId);
}
