package com.fykj.scaffold.security.business.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.security.business.domain.dto.ApiManageDto;
import com.fykj.scaffold.security.business.domain.entity.ApiManage;
import com.fykj.scaffold.security.business.domain.params.ApiManageParams;
import result.JsonResult;
import result.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author yangx
 */
public interface IApiManageService {

    /**
     * 通过获取的HttpServletRequest拿到调第三方接口所需的信息
     *
     * @param request
     * @return
     */
    String getParamsOfHttpRequestNeed(HttpServletRequest request) throws IOException, NoSuchAlgorithmException, URISyntaxException;

    /**
     * 通过url的code获取该第三方接口的信息
     *
     * @param urlCode
     * @return
     */
    ApiManage detailByUrlCode(String urlCode);

    /**
     * 按条件分页查询第三方接口
     *
     * @param params
     * @return
     */
    IPage<ApiManageDto> findByPage(ApiManageParams params);

    /**
     * 批量更改状态
     *
     * @param idList
     * @return
     */
    boolean statusChangeByIds(List<String> idList);

    /**
     * 通过接口id查询接口信息及接口参数信息
     *
     * @param id
     * @return
     */
    ApiManageDto getApiInfoAndParamInfo(String id);

    /**
     * 保存接口信息以及参数信息
     *
     * @param apiManageDto
     * @return
     */
    boolean saveApiInfoAndParams(ApiManageDto apiManageDto);

    /**
     * 验证code是否重复
     *
     * @param code
     * @return
     */
    boolean validate(String code);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    boolean removeBatch(List<String> idList);
}
