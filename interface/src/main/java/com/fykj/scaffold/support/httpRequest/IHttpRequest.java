package com.fykj.scaffold.support.httpRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author yangx
 */
public interface IHttpRequest {


    /**
     * httpClient调第三方接口
     *
     * @param paramsMap  客户端请求参数集
     * @param url 第三方请求接口
     * @return
     * @throws IOException
     */
    String callThirdPartyRequest(Map<String,String[]> paramsMap, String url, String paramType) throws IOException, URISyntaxException;
}
