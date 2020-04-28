package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.dto.AuditDto;
import com.fykj.scaffold.cms.domain.dto.ContentCategoryPropValueDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryContent;
import com.fykj.scaffold.cms.domain.entity.CmsContent;
import com.fykj.scaffold.cms.domain.vo.PropVo;
import com.fykj.scaffold.cms.mapper.CmsContentMapper;
import com.fykj.scaffold.cms.service.ICmsCategoryContentService;
import com.fykj.scaffold.cms.service.ICmsContentPropService;
import com.fykj.scaffold.cms.service.ICmsContentService;
import com.fykj.scaffold.cms.service.ICmsReadingRecordService;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.support.conns.Cons;
import com.fykj.scaffold.support.utils.BeanUtil;
import constants.Mark;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-10-26
 */
@Service
public class CmsContentServiceImpl extends BaseServiceImpl<CmsContentMapper, CmsContent> implements ICmsContentService {

    @Autowired
    private ICmsReadingRecordService cmsReadingRecordService;

    @Autowired
    private ICmsCategoryContentService cmsCategoryContentService;

    @Autowired
    private ICmsContentPropService contentPropService;

    @Override
    public boolean save(CmsContent entity) {
        setContentCommon(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(CmsContent entity) {
        setContentCommon(entity);
        return super.updateById(entity);
    }

    private void setContentCommon(CmsContent entity) {
        //是否需要审核为true时，设置为待审核
        if (entity.getAudit()) {
            //设置待审核
            entity.setAuditStatus(Cons.AUDIT_STATUS_CHECK);
        } else {
            //设置审核通过
            entity.setAuditStatus(Cons.AUDIT_STATUS_PASS);
        }
    }

    @Override
    public boolean audit(AuditDto dto) {
        if (StringUtil.isEmpty(dto.getIds())) {
            throw new BusinessException(ResultCode.NOT_VALID, "请选择需要审核的数据");
        }
        String[] idList = dto.getIds().split(Mark.COMMA);
        for (String id : idList) {
            CmsContent entity = getById(id);
            entity.setAuditStatus(dto.isStatus() ? Cons.AUDIT_STATUS_PASS : Cons.AUDIT_STATUS_NOT_PASS);
            entity.setAuditMemo(dto.getCheckOpinion());
            super.updateById(entity);
        }
        return true;
    }

    @Override
    public List<IdTextVo> fuzzyQueryByTitle(String title) {
        List<IdTextVo> idTextVos = new ArrayList<>();
        lambdaQuery().like(CmsContent::getTitle, title).list().forEach(item -> {
            IdTextVo vo = new IdTextVo();
            vo.setId(item.getId());
            vo.setText(item.getTitle());
            idTextVos.add(vo);
        });
        return idTextVos;
    }

    @Override
    public ContentCategoryPropValueDto findDetailById(String categoryId, String id) {
        CmsCategoryContent cmsCategoryContent=cmsCategoryContentService.findByCategoryIdAndContentId(categoryId, id);
        if(!cmsCategoryContent.getRecycling()){
            baseMapper.updateActualReadingById(id);
            cmsReadingRecordService.saveReadingRecord(id);
            CmsContent entity = super.getById(id);
            int readingNum = entity.getActualReading() == null?0:entity.getActualReading();
            int recordCount = cmsReadingRecordService.countReadingRecordByContentId(id);
            if(recordCount >= readingNum){
                entity.setActualReading(recordCount);
                baseMapper.updateById(entity);
            }
            ContentCategoryPropValueDto dto = new ContentCategoryPropValueDto();
            BeanUtil.copyProperties(entity,dto);
           List<PropVo> propVos = contentPropService.getListByContentId(id);
            dto.setPropAndValue(propVos);
            return dto;
        }else{
            return null;
        }
    }

    @Override
    public CmsContent getDetailById(String id) {
        return super.getById(id);
    }

}
