package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsContentAttachment;
import com.fykj.scaffold.cms.domain.vo.AttachVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-10-30
 */
public interface ICmsContentAttachmentService extends IService<CmsContentAttachment> {

    /**
     * 保存
     * @param vo
     * @return
     */
    boolean saveAttach(AttachVo vo);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    boolean deleteByIds(List<String> idList);

    /**
     * 获取内容附件
     * @param contentId
     * @return
     */
    List<CmsContentAttachment> findListByContentId(String contentId);
}
