package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.dto.CascaderDto;
import com.fykj.scaffold.cms.domain.entity.CmsCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangm
 * @since 2019-10-25
 */
public interface ICmsCategoryService extends IService<CmsCategory> {

    /**
     * 栏目树组装
     *
     * @return 分类树
     */
    List<CascaderDto> cascader();

    /**
     * 获取级联树
     * @return
     */
    List<CmsCategory> tree();

    /**
     * 获取指定栏目的下一级栏目列表
     *
     * @param parentId 指定栏目主键
     * @return 下一级栏目列表
     */
    List<CascaderDto> findByParent(String parentId);

    /**
     * 设置启用/禁用
     *
     * @param id 主键
     * @return 更新结果
     */
    boolean setEnable(String id);

    /**
     * 获取第一级栏目
     * @return
     */
    List<CmsCategory> findParentAll();

    /**
     * 根据第一级id 获取第二级栏目
     * @param parentId
     * @return
     */
    List<CmsCategory> findChildAllByParentId(String parentId);

    /**
     * 获取客户案例
     * @param parentId
     * @return
     */
    List<CmsCategory> findCustomerCase(String parentId);

    /**
     * 获取 首页客户案例
     * @param parentId
     * @return
     */
    List<CmsCategory> findCaseList(String parentId);
}
