package com.fykj.scaffold.cms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.cms.domain.entity.CmsComment;
import com.fykj.scaffold.cms.domain.params.CmsCommentParams;
import com.fykj.scaffold.cms.domain.vo.CmsCommentVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuew
 * @since 2019-11-06
 */
public interface CmsCommentMapper extends BaseMapper<CmsComment> {
    /**
     * 后台分页查询信息
     *
     * @param page 分页参数
     * @param params 查询条件
     * @return
     */
    IPage<CmsCommentVo> getListWithQuery(IPage<CmsCommentVo> page, @Param("params") CmsCommentParams params);
}
