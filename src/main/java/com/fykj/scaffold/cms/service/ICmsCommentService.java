package com.fykj.scaffold.cms.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsComment;
import com.fykj.scaffold.cms.domain.params.CmsCommentParams;
import com.fykj.scaffold.cms.domain.vo.CmsCommentVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xuew
 * @since 2019-11-06
 */
public interface ICmsCommentService extends IService<CmsComment> {

    /**
     * 回复
     * @param id 回复的评论id
     * @param content 内容
     * @return 是否成功
     */
    boolean reply(String id, String content);

    /**
     * 后台分页查询信息
     *
     * @param page 分页对象
     * @param params 参数
     * @return 分页结果
     */
    IPage<CmsCommentVo> getListWithQuery(IPage<CmsCommentVo> page, CmsCommentParams params);
}
