package com.fykj.scaffold.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsCategory;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryContent;
import com.fykj.scaffold.cms.domain.entity.CmsContent;
import com.fykj.scaffold.cms.domain.entity.CmsContentAttachment;
import com.fykj.scaffold.cms.domain.params.ApiCmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.vo.AboutUsVo;
import com.fykj.scaffold.cms.domain.vo.CategoryContentVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentClientsVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentVo;
import com.fykj.scaffold.cms.mapper.CmsCategoryContentMapper;
import com.fykj.scaffold.cms.service.ICmsCategoryContentService;
import com.fykj.scaffold.cms.service.ICmsCategoryService;
import com.fykj.scaffold.cms.service.ICmsContentAttachmentService;
import com.fykj.scaffold.cms.service.ICmsContentService;
import com.fykj.scaffold.support.utils.BeanUtil;
import constants.Mark;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-10-29
 */
@Service
public class CmsCategoryContentServiceImpl extends BaseServiceImpl<CmsCategoryContentMapper, CmsCategoryContent> implements ICmsCategoryContentService {

    @Autowired
    private ICmsCategoryService categoryService;

    @Autowired
    private ICmsContentAttachmentService contentAttachmentService;

    @Autowired
    private ICmsContentService contentService;

    @Override
    public boolean setStick(String id) {
        CmsCategoryContent entity = getById(id);
        entity.setStick(!entity.getStick());
        return super.updateById(entity);
    }

    @Override
    public boolean setHot(String id) {
        CmsCategoryContent entity = getById(id);
        entity.setHot(!entity.getHot());
        return super.updateById(entity);
    }

    @Override
    public IPage<CmsContentVo> getListWithQuery(IPage<CmsContentVo> page, CmsContentParams cmsContentParams) {
        return baseMapper.getListWithQuery(page,cmsContentParams);
    }

    @Override
    public boolean saveContent(CategoryContentVo vo) {
        CmsContent content = new CmsContent();
        BeanUtil.copyProperties(vo,content);
        if(vo.getVirtualReading()==null){
            content.setVirtualReading(0);
        }
        if (!contentService.save(content)) {
            return false;
        }
        List<String[]> categoryIdList = vo.getCategoryId();
        for (String[] categoryIds : categoryIdList) {
            int len = categoryIds.length - 1;
            CmsCategoryContent categoryContent = new CmsCategoryContent();
            categoryContent.setCategoryId(categoryIds[len]);
            categoryContent.setParentId(len==0?"":categoryIds[len-1]);
            categoryContent.setCategoryList(String.join(Mark.COMMA,Arrays.asList(categoryIds)));
            categoryContent.setHot(vo.getHot());
            categoryContent.setStick(vo.getStick());
            categoryContent.setComment(vo.getComment());
            categoryContent.setImportantLevel(vo.getImportantLevel());
            categoryContent.setContentId(content.getId());
            save(categoryContent);
        }
        return true;
    }

    @Override
    public boolean updateContent(CategoryContentVo vo) {
        if (!removeById(vo.getId())) {
            return false;
        }
        List<String[]> categoryIdList = vo.getCategoryId();
        for (String[] categoryIds : categoryIdList) {
            int len = categoryIds.length - 1;
            CmsCategoryContent categoryContent = new CmsCategoryContent();
            categoryContent.setCategoryId(categoryIds[len]);
            categoryContent.setParentId(len==0?"":categoryIds[len-1]);
            categoryContent.setCategoryList(String.join(Mark.COMMA,Arrays.asList(categoryIds)));
            categoryContent.setHot(vo.getHot());
            categoryContent.setStick(vo.getStick());
            categoryContent.setComment(vo.getComment());
            categoryContent.setImportantLevel(vo.getImportantLevel());
            categoryContent.setContentId(vo.getContentId());
            categoryContent.setSequence(vo.getSequence());
            save(categoryContent);
        }
        CmsContent content = contentService.getById(vo.getContentId());
        BeanUtil.copyProperties(vo,content,"id");
        return contentService.updateById(content);
    }

    @Override
    public CategoryContentVo getCategoryContentById(String id) {
        CategoryContentVo vo = new CategoryContentVo();
        CmsCategoryContent categoryContent = getById(id);
        BeanUtil.copyProperties(categoryContent,vo);
        CmsContent content = contentService.getById(categoryContent.getContentId());
        BeanUtil.copyProperties(content,vo,"id","version");
        String[] categories = categoryContent.getCategoryList().split(Mark.COMMA);
        List<String[]> categoryId = new ArrayList<>();
        categoryId.add(categories);
        vo.setCategoryId(categoryId);
        return vo;
    }

    @Override
    public boolean recyclingByIds(List<String> idList, boolean recycling) {
        for (String id : idList) {
            CmsCategoryContent categoryContent = getById(id);
            categoryContent.setRecycling(recycling);
            if (!updateById(categoryContent)) {
                throw new BusinessException(ResultCode.FAIL, recycling?"加入回收站失败":"恢复数据失败");
            }
        }
        return true;
    }

    @Override
    public IPage<CmsContentVo> findByPage(IPage<CmsContentVo> page, ApiCmsContentParams params){
        return baseMapper.findByPage(page, params);
    }

    @Override
    public CmsCategoryContent findByCategoryIdAndContentId(String categoryId, String contentId) {
        CmsCategoryContent entity;
        entity = lambdaQuery().eq(CmsCategoryContent::getCategoryId,categoryId)
                .eq(CmsCategoryContent::getContentId,contentId)
                .one();
        return entity;
    }

    @Override
    public CmsContentClientsVo findByHonor(String categoryId) {
        List<CmsCategoryContent> list = lambdaQuery().eq(CmsCategoryContent::getCategoryId,categoryId).orderByDesc(CmsCategoryContent::getCreateDate).list();
        if(list.isEmpty()){
            return null;
        }
        CmsContent cmsContent = contentService.getById(list.get(0).getContentId());
        if(cmsContent == null){
            return null;
        }
        CmsContentClientsVo vo = new CmsContentClientsVo();
        BeanUtil.copyProperties(cmsContent,vo);
        List<CmsContentAttachment> attachmentList = contentAttachmentService.findListByContentId(cmsContent.getId());
        vo.setContentAttachmentList(attachmentList);
        return vo;
    }

    @Override
    public AboutUsVo findByAboutUs(String categoryId) {
        List<CmsCategory> categoryList = categoryService.findChildAllByParentId(categoryId);
        if(categoryList.isEmpty()){
            return null;
        }
        AboutUsVo vo = new AboutUsVo();
        categoryList.forEach(item -> {
            if("公司介绍".equals(item.getName())){
                vo.setIntroduceVo(findByHonor(item.getId()));
            } else if("发展历程".equals(item.getName())){
                List<CmsContentClientsVo> voList = findByCategoryId(item.getId());
                vo.setDevelopmentList(voList);
            } else if("联系我们".equals(item.getName())){
                List<CmsContentClientsVo> voList = findByCategoryId(item.getId());
                vo.setLinkUsList(voList);
            }
        });
        return vo;
    }

    public List<CmsContentClientsVo> findByCategoryId(String categoryId) {
        List<CmsCategoryContent> list = lambdaQuery().eq(CmsCategoryContent::getCategoryId,categoryId).orderByDesc(CmsCategoryContent::getCreateDate).list();
        if(list.isEmpty()){
            return null;
        }
        List<CmsContentClientsVo> voList = new ArrayList<>();
        list.forEach(it->{
            CmsContent cmsContent = contentService.getById(it.getContentId());
            CmsContentClientsVo vo = new CmsContentClientsVo();
            BeanUtil.copyProperties(cmsContent,vo);
            voList.add(vo);
        });
        return voList;
    }
}
