package com.fykj.scaffold.security.business.service;

import com.fykj.scaffold.security.business.domain.entity.ApiParamManage;

import java.util.List;


public interface IApiParamManageService {

    /**
     * 获取某第三方接口参数个数
     * @param urlId
     * @return
     */
    Integer getParamNumByUrlId(String urlId);

    /**
     * 通过第三方接口id获取该接口下的参数列表
     *
     * @param urlId
     * @return
     */
    List<ApiParamManage> getParamByUrlId(String urlId);

    /**
     * 保存参数信息
     *
     * @param apiParamManageList
     * @return
     */
    boolean saveParamInfo(List<ApiParamManage> apiParamManageList);

    /**
     * 根据接口Id移除参数
     *
     * @param urlId
     * @return
     */
    boolean removeByApiId(List<String> urlId);

}
