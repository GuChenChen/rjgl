package com.fykj.scaffold.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsComment;
import com.fykj.scaffold.cms.domain.params.CmsCommentParams;
import com.fykj.scaffold.cms.domain.vo.CmsCommentVo;
import com.fykj.scaffold.cms.mapper.CmsCommentMapper;
import com.fykj.scaffold.cms.service.ICmsCommentService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import exception.BusinessException;
import org.springframework.stereotype.Service;
import result.ResultCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuew
 * @since 2019-11-06
 */
@Service
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsCommentMapper, CmsComment> implements ICmsCommentService {

    @Override
    public boolean reply(String id, String content) {
        CmsComment comment = baseMapper.selectById(id);
        if (comment == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "找不到资源");
        }
        comment.setReplyContent(content);
        comment.setReply(true);
        comment.setReplyTime(LocalDateTime.now());
        BackendUserDetail currentUser = getUser();
        if (currentUser == null) {
            throw new BusinessException(ResultCode.ERROR, "系统错误无法检测到当前登录人");
        }
        comment.setReplyPerson(currentUser.getNickName());
        comment.setReplyPersonId(currentUser.getId());
        return updateById(comment);
    }

    @Override
    public IPage<CmsCommentVo> getListWithQuery(IPage<CmsCommentVo> page, CmsCommentParams params) {
        return baseMapper.getListWithQuery(page, params);
    }
}
