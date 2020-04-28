package com.fykj.scaffold.support.httpRequest.impl;
import java.io.IOException;

import com.fykj.scaffold.support.httpRequest.HttpRequestCons;
import com.fykj.scaffold.support.httpRequest.IHttpRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangx
 */
@Component(HttpRequestCons.HTTP_REQUEST_BEAN_PREFIX + HttpRequestCons.HTTP_REQUEST_OF_GET_IMPL_BEAN)
@Slf4j
public class HttpRequestOfPostUtil implements IHttpRequest {

    @Override
    public String callThirdPartyRequest(Map<String,String[]> paramsMap, String url,String paramType) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36"));
            httpPost.setHeader(new BasicHeader("token","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjMxMmMyMmFhLWYxOGYtNDA5Mi05YTRmLTc5Mzg1YjY1ODA1MSIsIm5hbWUiOiLnrqHnkIblkZgiLCJtb2JpbGUiOiIxODg4ODg4ODg4OCIsIndlYnNpdGUiOiJjZW50ZXIiLCJleHAiOjE1NzI5NTA2NzZ9.qfOamx-SasBbM-JCilK__DbaCBp5YlWZ7DNJ4_jG6g4"));
            if("paramTypeOne".equalsIgnoreCase(paramType)){
                paramTransferToForm(paramsMap, httpPost);
            }else if("paramTypeTwo".equalsIgnoreCase(paramType)){
                paramTransferToJson(paramsMap, httpPost);
            }
            String entityStr;
            try(CloseableHttpResponse response = httpClient.execute(httpPost)){
                HttpEntity entity = response.getEntity();
                entityStr = EntityUtils.toString(entity);
                return entityStr;
            }
        }
    }

    public void paramTransferToForm(Map<String,String[]> paramsMap,HttpPost httpPost) throws UnsupportedEncodingException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        paramsMap.forEach((key,value) -> {
            formparams.add(new BasicNameValuePair(key,value[0]));
        });
        UrlEncodedFormEntity ueEntity;
        ueEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httpPost.setEntity(ueEntity);
    }

    public void paramTransferToJson(Map < String, String [] > paramsMap, HttpPost httpPost) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        String parameter = gson.toJson(paramsMap);
        StringEntity se = new StringEntity(parameter);
        se.setContentType("application/json");
        httpPost.setEntity(se);
    }
}
