package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.XssTagAttributeProtocol;
import com.fykj.scaffold.security.business.domain.params.XssTagParams;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoc
 * @since 2019-10-30
 */
public interface IXssTagAttributeProtocolService extends IService<XssTagAttributeProtocol> {
    /**
     * 模糊查询
     * @param params
     * @return
     */
    IPage<XssTagAttributeProtocol> findByPage(XssTagParams params);

    /**
     * 新增
     * @param info
     */
    void saveInfo(XssTagAttributeProtocol info);

    /**
     * 修改
     * @param info
     */
    void updateInfo(XssTagAttributeProtocol info);

    /**
     * tag白名单
     * @return String[] tags
     */
    String[] getTags();

    /**
     * tag和attribute白名单
     * @return Map<Tag,List<AttributeKey>>
     */
    Map<String, List<String>> getTagAndAttrKey();

    /**
     * tag和对应的attribute_key和attribute_value白名单
     * @return Map<Tag, Map<AttributeKey,AttributeValue>>
     */
    Map<String, Map<String,String>> getTagAndAttrKeyVal();

    /**
     * tag,attribute_ke,protocol白名单
     * @return Map<tag,Map<attribute_key, List<protocol>>>
     */
    Map<String,Map<String, List<String>>> getTagAndKeyAndProtocol();
}
