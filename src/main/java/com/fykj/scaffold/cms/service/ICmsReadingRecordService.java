package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.dto.AuditDto;
import com.fykj.scaffold.cms.domain.entity.CmsContent;
import com.fykj.scaffold.cms.domain.entity.CmsReadingRecord;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangx
 * @since 2019-11-22
 */
public interface ICmsReadingRecordService extends IService<CmsReadingRecord> {

    /**
     * 保存阅读记录信息
     * @param contentId
     * @return
     */
    boolean saveReadingRecord(String contentId);

    /**
     * 统计某内容阅读记录数
     * @param contentId
     * @return
     */
    Integer countReadingRecordByContentId(String contentId);
}
