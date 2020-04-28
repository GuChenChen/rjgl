package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryLabel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-11-04
 */
public interface ICmsCategoryLabelService extends IService<CmsCategoryLabel> {

    boolean deleteByCategoryId(String categoryId);

    List<String> getByCategoryId(String categoryId);
}
