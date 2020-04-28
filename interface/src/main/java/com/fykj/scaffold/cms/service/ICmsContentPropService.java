package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.dto.PropValueDto;
import com.fykj.scaffold.cms.domain.entity.CmsContentProp;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentPropParams;
import com.fykj.scaffold.cms.domain.vo.PropVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-12-23
 */
public interface ICmsContentPropService extends IService<CmsContentProp> {

    /**
     * 根据栏目id获取扩展属性
     * @param categoryId
     * @param contentId
     * @return
     */
    List<PropVo> getByCategoryId(String categoryId, String contentId);


    /**
     * 后台分页查询信息
     *
     * @param page
     * @param params
     * @return
     */
    IPage<PropVo> getListWithQuery(IPage<PropVo> page, CmsContentPropParams params);

    /**
     * 保存公共属性
     * @param propValue
     * @return
     */
    boolean savePubPropValue(PropValueDto propValue);

    /**
     * 根据内容id查询属性
     * @param contentId
     * @return
     */
    List<PropVo> getListByContentId(String contentId);
}
