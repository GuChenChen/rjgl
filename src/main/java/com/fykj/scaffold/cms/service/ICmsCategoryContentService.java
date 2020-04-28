package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.CmsCategoryContent;
import com.fykj.scaffold.cms.domain.params.ApiCmsContentParams;
import com.fykj.scaffold.cms.domain.params.CmsContentParams;
import com.fykj.scaffold.cms.domain.vo.AboutUsVo;
import com.fykj.scaffold.cms.domain.vo.CategoryContentVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentClientsVo;
import com.fykj.scaffold.cms.domain.vo.CmsContentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-10-29
 */
public interface ICmsCategoryContentService extends IService<CmsCategoryContent> {


    /**
     * 设置置顶/取消置顶
     * @param id
     * @return
     */
    boolean setStick(String id);

    /**
     * 设置热门/取消热门
     * @param id
     * @return
     */
    boolean setHot(String id);

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param cmsContentParams
     * @return
     */
    IPage<CmsContentVo> getListWithQuery(IPage<CmsContentVo> page, CmsContentParams cmsContentParams);

    /**
     * 保存栏目内容管理
     * @param vo
     * @return
     */
    boolean saveContent(CategoryContentVo vo);

    /**
     * 更新栏目内容管理
     * @param vo
     * @return
     */
    boolean updateContent(CategoryContentVo vo);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    CategoryContentVo getCategoryContentById(String id);

    /**
     * 回收站操作
     * @param idList
     * @param recycling
     * @return
     */
    boolean recyclingByIds(List<String> idList, boolean recycling);

    /**
     * 客户端分页列表 - 软件测试/新闻咨询/客户案例/合作伙伴/关于我们
     * @param page
     * @param params
     * @return
     */
    IPage<CmsContentVo> findByPage(IPage<CmsContentVo> page, ApiCmsContentParams params);

    /**
     * 获取荣誉资质
     * @param categoryId
     * @return
     */
    CmsContentClientsVo findByHonor(String categoryId);

    /**
     * 获取关于我们
     * @param categoryId
     * @return
     */
    AboutUsVo findByAboutUs(String categoryId);

    /**
     * 获取内容
     * @param categoryId
     * @param contentId
     * @return
     */
    CmsCategoryContent findByCategoryIdAndContentId(String categoryId,String contentId);
}
