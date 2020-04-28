package com.fykj.scaffold.support.httpRequest.impl;
import com.alibaba.druid.util.StringUtils;
import com.fykj.scaffold.support.httpRequest.HttpRequestCons;
import com.fykj.scaffold.support.httpRequest.IHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * @author yangx
 */
@Component(HttpRequestCons.HTTP_REQUEST_BEAN_PREFIX + HttpRequestCons.HTTP_REQUEST_OF_POST_IMPL_BEAN)
@Slf4j
public class HttpRequestOfGetUtil implements IHttpRequest {

    @Override
    public String callThirdPartyRequest(Map<String,String[]> paramsMap, String url,String paramType) throws IOException, URISyntaxException {
        //创建默认的httpClient实例
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            //拼接url参数
            url = getString(paramsMap, url);
            URL url1 = new URL(url);
            URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
            //创建HttpGet实例
            HttpGet httpGet = new HttpGet(uri);
            //浏览器表示
            httpGet.setHeader(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36"));
            //执行请求
            try(CloseableHttpResponse response = httpClient.execute(httpGet)){
                //获得响应的实体对象
                HttpEntity entity = response.getEntity();
                // 使用Apache提供的工具类进行转换成字符串
                return EntityUtils.toString(entity, "UTF-8");
            }
        }
    }

    public static String getString(Map<String, String[]> paramsMap, String url) {
        StringBuilder buffer = new StringBuilder();
        if (paramsMap != null && !paramsMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                for (String s : entry.getValue()) {
                    if (StringUtils.isEmpty(buffer.toString())) {
                        buffer.append("?");
                    } else {
                        buffer.append("&");
                    }
                    buffer.append(entry.getKey()).append("=").append(s);
                }
            }
        }
        url += buffer.toString();
        return url;
    }
}
