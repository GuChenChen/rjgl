package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryProp;
import com.fykj.scaffold.cms.domain.params.CmsCategoryPropParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-11-08
 */
public interface ICmsCategoryPropService extends IService<CmsCategoryProp> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param propParams
     * @return
     */
    IPage<CmsCategoryProp> getListWithQuery(IPage<CmsCategoryProp> page, CmsCategoryPropParams propParams);

    /**
     * 通过栏目id查询栏目属性列表
     *
     * @param categoryId
     * @return
     */
    List<CmsCategoryProp> getCategoryPropListByCategoryId(String categoryId);

    /**
     * 分类下的属性是否存在
     * @param categoryId
     * @param propId
     * @return
     */
    boolean checkExistCategoryIdAndPropId(String categoryId, String propId);
}
