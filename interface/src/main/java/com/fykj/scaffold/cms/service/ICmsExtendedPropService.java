package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsExtendedProp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-11-06
 */
public interface ICmsExtendedPropService extends IService<CmsExtendedProp> {

    List<CmsExtendedProp> getExtendedPropList();
}
