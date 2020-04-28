package com.fykj.scaffold.support.httpRequest;

import com.alibaba.fastjson.JSONObject;
import com.fykj.scaffold.support.utils.SpringContextUtil;
import result.JsonResult;
import result.Result;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author yangx
 */
public class HttpRequestUtil {

    private static IHttpRequest getInstance(String type) {
        return SpringContextUtil.getBean(HttpRequestCons.HTTP_REQUEST_BEAN_PREFIX + type);
    }

    /**
     * 调第三方接口（get/post)
     *
     * @param paramsMap 客户端请求参数集
     * @param type 第三方接口类型
     * @param url 第三方接口url
     * @return
     */
    public static String callThirdPartyRequest(Map<String,String[]> paramsMap, String type, String url,String paramType) throws IOException, URISyntaxException {
        IHttpRequest requester = getInstance(type);
        return requester.callThirdPartyRequest(paramsMap,url,paramType);
    }
}
