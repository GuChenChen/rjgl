package com.fykj.scaffold.support.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件对应content type工具类
 *
 * @author zhangzhi.
 */
@Slf4j
public class ContentTypeUtil {

    private static Map<String, String> contentTypeMap = new HashMap<>();

    static {
        contentTypeMap.put("bmp", "image/bmp");
        contentTypeMap.put("gif", "image/gif");
        contentTypeMap.put("jpg", "image/jpeg");
        contentTypeMap.put("jpeg", "image/jpeg");
        contentTypeMap.put("png", "image/png");
        contentTypeMap.put("tiff", "image/tiff");
        contentTypeMap.put("tif", "image/tiff");
        contentTypeMap.put("css", "text/css");
        contentTypeMap.put("html", "text/html");
        contentTypeMap.put("xml", "text/xml");
        contentTypeMap.put("txt", "text/plain");
        contentTypeMap.put("json", "text/json");
        contentTypeMap.put("flac", "audio/flac");
        contentTypeMap.put("mp3", "audio/mp3");
        contentTypeMap.put("wav", "audio/wav");
        contentTypeMap.put("wma", "audio/x-ms-wma");
    }

    /**
     * 获取content type
     *
     * @param ext 文件后缀名
     * @return 对应content type
     */
    public static String getContentType(String ext) {
        String result = contentTypeMap.get(ext);
        if (result == null) {
            return "application/octet-stream";
        }
        return result;
    }
}
