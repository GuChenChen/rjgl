package com.fykj.scaffold.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.dto.CascaderDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategory;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryLabel;
import com.fykj.scaffold.cms.mapper.CmsCategoryMapper;
import com.fykj.scaffold.cms.service.ICmsCategoryLabelService;
import com.fykj.scaffold.cms.service.ICmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-10-25
 */
@Service
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategory> implements ICmsCategoryService {

    @Autowired
    private ICmsCategoryLabelService categoryLabelService;

    @Override
    public List<CmsCategory> list() {
        return list(new QueryWrapper<CmsCategory>().orderByAsc("sequence"));
    }

    @Override
    public List<CascaderDto> cascader() {
        List<CascaderDto> categories = findByParent(null);
        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptyList();
        }
        categories.forEach(it -> buildTree(it));
        return categories;
    }

    @Override
    public List<CmsCategory> tree() {
        List<CmsCategory> categories = findByParent(null, true);
        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptyList();
        }
        categories.forEach(it -> buildTree(it, null));
        return categories;
    }

    @SuppressWarnings("unchecked")
    public List<CmsCategory> findByParent(String parentId, Boolean enable) {
        return lambdaQuery().eq(StringUtil.isNotEmpty(parentId), CmsCategory::getParentId, parentId)
                .eq(enable != null, CmsCategory::getStatus, true)
                .isNull(StringUtil.isEmpty(parentId), CmsCategory::getParentId)
                .orderByAsc(CmsCategory::getSequence).list();
    }

    /**
     * 构建分类树-递归查询所有子分类
     *
     * @param category 树分类根节点
     */
    private void buildTree(CmsCategory category, Boolean enable) {
        List<CmsCategory> subCategories = findByParent(category.getId(), enable);
        category.setSubCategories(subCategories);
        if (!CollectionUtils.isEmpty(subCategories)) {
            subCategories.forEach(it -> buildTree(it, enable));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CascaderDto> findByParent(String parentId) {
        return baseMapper.findByParent(parentId);
    }

    @Override
    public boolean setEnable(String id) {
        CmsCategory cmsCategory = getById(id);
        cmsCategory.setStatus(!cmsCategory.getStatus());
        return super.updateById(cmsCategory);
    }


    /**
     * 构建分类树-递归查询所有子分类
     *
     * @param category 树分类根节点
     */
    private void buildTree(CascaderDto category) {
        List<CascaderDto> subCategories = baseMapper.findByParent(category.getValue());
        category.setChildren(subCategories);
        if (!CollectionUtils.isEmpty(subCategories)) {
            subCategories.forEach(it -> buildTree(it));
        }
    }

    @Override
    public boolean save(CmsCategory category) {
        getCategoryLevel(category);
        if (super.save(category)) {
            return saveLabel(category.getLabels(),category.getId());
        }
        return false;
    }

    @Override
    public boolean updateById(CmsCategory category) {
        getCategoryLevel(category);
        if (super.updateById(category)) {
            return saveLabel(category.getLabels(),category.getId());
        }
        return false;
    }

    private boolean saveLabel(String[] labels, String categoryId){
        categoryLabelService.deleteByCategoryId(categoryId);
        for (String label : labels) {
            CmsCategoryLabel cmsCategoryLabel = new CmsCategoryLabel();
            cmsCategoryLabel.setCategoryId(categoryId);
            cmsCategoryLabel.setLabelId(label);
            categoryLabelService.save(cmsCategoryLabel);
        }
        return true;
    }

    private void getCategoryLevel(CmsCategory category) {
        String parentId = category.getParentId();
        if (StringUtil.isNotEmpty(parentId)) {
            CmsCategory parent = getById(parentId);
            category.setLevel(parent.getLevel() + 1);
        }
    }

    @Override
    public CmsCategory getById(Serializable id){
        CmsCategory cmsCategory = super.getById(id);
        List<String> labels = categoryLabelService.getByCategoryId(cmsCategory.getId());
        cmsCategory.setLabels(labels.toArray(new String[labels.size()]));
        return cmsCategory;
    }

    @Override
    public List<CmsCategory> findParentAll() {
        return lambdaQuery().eq(CmsCategory::getLevel,1).eq(CmsCategory::getStatus,true).orderByAsc(CmsCategory::getSequence).list();
    }

    @Override
    public List<CmsCategory> findChildAllByParentId(String parentId) {
        return lambdaQuery().eq(CmsCategory::getParentId,parentId).eq(CmsCategory::getStatus,true).orderByAsc(CmsCategory::getSequence).list();
    }

    @Override
    public List<CmsCategory> findCustomerCase(String parentId) {
        List<CmsCategory> list = findChildAllByParentId(parentId);
        if(list.isEmpty()){
            return null;
        }
        list = list.stream().map(it->{
            List<CmsCategory> categoryList = findCustomerCase(it.getId());
            it.setSubCategories(categoryList);
            return it;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<CmsCategory> findCaseList(String parentId) {
        List<CmsCategory> list = findChildAllByParentId(parentId);
        if(list.isEmpty()){
            return null;
        }
        List<CmsCategory> caseList = new ArrayList<>();
        list.forEach(it->{
            List<CmsCategory> categoryList = findCustomerCase(it.getId());
            caseList.addAll(categoryList);
        });
        return caseList;
    }
}
