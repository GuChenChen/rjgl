package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryProp;
import com.fykj.scaffold.cms.domain.params.CmsCategoryPropParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangm
 * @since 2019-11-08
 */
public interface CmsCategoryPropMapper extends BaseMapper<CmsCategoryProp> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param cmsContentParams
     * @return
     */
    IPage<CmsCategoryProp> getListWithQuery(IPage<CmsCategoryProp> page, @Param("params") CmsCategoryPropParams cmsContentParams);

    /**
     * 后台查询信息列表
     *
     * @param categoryId
     * @return
     */
    List<CmsCategoryProp> getList(String categoryId);
}
