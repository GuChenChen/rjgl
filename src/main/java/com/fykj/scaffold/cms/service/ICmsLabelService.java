package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsLabel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-11-04
 */
public interface ICmsLabelService extends IService<CmsLabel> {

    List<CmsLabel> getLabelList();
}
