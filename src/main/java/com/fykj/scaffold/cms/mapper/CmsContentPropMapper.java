package com.fykj.scaffold.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.cms.domain.entity.CmsContentProp;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentPropParams;
import com.fykj.scaffold.cms.domain.vo.PropVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangm
 * @since 2019-12-23
 */
public interface CmsContentPropMapper extends BaseMapper<CmsContentProp> {
    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<PropVo> getListWithQuery(IPage<PropVo> page, @Param("params") CmsContentPropParams params);

    /**
     * 根据内容id查询属性
     * @param contentId
     * @return
     */
    List<PropVo> getListByContentId(@Param("contentId") String contentId);
}
