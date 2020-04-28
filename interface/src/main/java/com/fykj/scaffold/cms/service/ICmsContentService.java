package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.dto.AuditDto;
import com.fykj.scaffold.cms.domain.dto.ContentCategoryPropValueDto;
import com.fykj.scaffold.cms.domain.entity.CmsContent;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-10-26
 */
public interface ICmsContentService extends IService<CmsContent> {

    /**
     * 审核
     * @param dto
     * @return
     */
    boolean audit(AuditDto dto);

    /**
     * 根据标题模糊查询
     * @param title 标题
     * @return 下拉
     */
    List<IdTextVo> fuzzyQueryByTitle(String title);

    /**
     * 客户端详情
     * @param id 内容id
     * @param categoryId 栏目id
     * @return 内容dto
     */
    ContentCategoryPropValueDto findDetailById(String categoryId, String id);

    /**
     * 后台管理系统详情
     * @param id 内容id
     * @return 内容实体
     */
    CmsContent getDetailById(String id);
}
