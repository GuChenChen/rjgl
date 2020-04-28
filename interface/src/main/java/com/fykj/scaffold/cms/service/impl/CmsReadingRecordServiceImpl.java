package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.dto.AuditDto;
import com.fykj.scaffold.cms.domain.entity.CmsContent;
import com.fykj.scaffold.cms.domain.entity.CmsReadingRecord;
import com.fykj.scaffold.cms.mapper.CmsContentMapper;
import com.fykj.scaffold.cms.mapper.CmsReadingRecordMapper;
import com.fykj.scaffold.cms.service.ICmsContentService;
import com.fykj.scaffold.cms.service.ICmsReadingRecordService;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.support.conns.Cons;
import constants.Mark;
import exception.BusinessException;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangx
 * @since 2019-11-22
 */
@Service
public class CmsReadingRecordServiceImpl extends BaseServiceImpl<CmsReadingRecordMapper, CmsReadingRecord> implements ICmsReadingRecordService {


    @Override
    public boolean saveReadingRecord(String contentId) {
        CmsReadingRecord entity = new CmsReadingRecord();
        entity.setContentId(contentId);
        entity.setReadingTime(LocalDateTime.now());
        return super.save(entity);
    }

    @Override
    public Integer countReadingRecordByContentId(String contentId) {
        return baseMapper.countReadingRecordById(contentId);
    }
}
