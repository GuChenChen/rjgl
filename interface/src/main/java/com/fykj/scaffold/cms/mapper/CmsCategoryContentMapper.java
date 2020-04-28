package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryContent;
import com.fykj.scaffold.cms.domain.params.ApiCmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.vo.CmsContentVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangm
 * @since 2019-10-29
 */
public interface CmsCategoryContentMapper extends BaseMapper<CmsCategoryContent> {

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param cmsContentParams
     * @return
     */
    IPage<CmsContentVo> getListWithQuery(IPage<CmsContentVo> page, @Param("params") CmsContentParams cmsContentParams);

    /**
     * 客户端列表查询
     * @param page
     * @param params
     * @return
     */
    IPage<CmsContentVo> findByPage(IPage<CmsContentVo> page, @Param("params") ApiCmsContentParams params);

}
