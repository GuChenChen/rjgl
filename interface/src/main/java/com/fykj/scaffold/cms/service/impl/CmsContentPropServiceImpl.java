package com.fykj.scaffold.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.dto.PropValueDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryProp;
import com.fykj.scaffold.cms.domain.entity.CmsContentProp;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentPropParams;
import com.fykj.scaffold.cms.domain.vo.PropVo;
import com.fykj.scaffold.cms.mapper.CmsContentPropMapper;
import com.fykj.scaffold.cms.service.ICmsCategoryPropService;
import com.fykj.scaffold.cms.service.ICmsContentPropService;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import result.ResultCode;
import utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-12-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmsContentPropServiceImpl extends BaseServiceImpl<CmsContentPropMapper, CmsContentProp> implements ICmsContentPropService {

    @Autowired
    private ICmsCategoryPropService categoryPropService;
    @Override
    public List<PropVo> getByCategoryId(String categoryId, String contentId) {
        //根据栏目查询栏目的属性
        List<CmsCategoryProp> categoryProps = categoryPropService.getCategoryPropListByCategoryId(categoryId);
        //查询栏目和内容下的属性值
        List<CmsContentProp> contentProps = getContentProp(categoryId,contentId);
        List<PropVo> propVos = new ArrayList<>();
        for (CmsCategoryProp prop : categoryProps) {
            PropVo propVo = new PropVo();
            propVo.setPropId(prop.getPropId());
            propVo.setPropName(prop.getPropName());
            CmsContentProp contentProp = null;
            if (!CollectionUtils.isEmpty(contentProps)) {
                contentProp = contentProps.stream().filter(it->it.getPropId().equals(prop.getPropId())).findFirst().orElse(null);
            }
            if (contentProp != null) {
                propVo.setPropValue(contentProp.getPropValue());
            }
            propVos.add(propVo);
        }
        return propVos;
    }

    private List<CmsContentProp> getContentProp(String categoryId, String contentId){
        return lambdaQuery().eq(CmsContentProp::getCategoryId,categoryId)
                .eq(CmsContentProp::getContentId,contentId).eq(CmsContentProp::getCategoryRelate,true)
                .list();
    }

    @Override
    public IPage<PropVo> getListWithQuery(IPage<PropVo> page, CmsContentPropParams params) {
        return baseMapper.getListWithQuery(page,params);
    }

    @Override
    public boolean savePubPropValue(PropValueDto propValue) {
        List<CmsContentProp> propValues = propValue.getPropValueList()
                .stream().filter(it->StringUtil.isNotEmpty(it.getPropValue()))
                .collect(Collectors.toList());
        //获取已有的公有属性
        List<CmsContentProp> props = getContentProp(propValue.getCategoryId(),propValue.getContentId());
        if (!CollectionUtils.isEmpty(props)) {
            List<String> idList = new ArrayList<>();
            props.forEach(it -> idList.add(it.getId()));
            //删除已有的公有属性
            if (!removeByIds(idList)) {
                throw new BusinessException(ResultCode.FAIL,"公共属性保存失败");
            }
        }
        //批量保存公有属性
        if (!saveBatch(propValues)) {
            throw new BusinessException(ResultCode.FAIL,"公共属性保存失败");
        }
        return true;
    }

    @Override
    public List<PropVo> getListByContentId(String contentId) {
        return baseMapper.getListByContentId(contentId);
    }
}
