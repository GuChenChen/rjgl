package com.fykj.scaffold.support.oss;

import com.fykj.scaffold.support.utils.SpringContextUtil;

import java.io.InputStream;

/**
 * 对象存储工具类
 */
public class OssSaveUtil {

    private static IOssSaver getInstance(String type) {
        return SpringContextUtil.getBean(OssCons.OSS_IMPL_BEAN_PREFIX + type);
    }


    /**
     * 对象存储保存
     *
     * @param is   输入流
     * @param type 保存位置（七牛云，#阿里云，#腾讯云，本地）#暂时不可用
     * @param ext  文件后缀
     * @return 资源访问路径
     */
    public static String save(InputStream is, String type, String ext) {
        IOssSaver saver = getInstance(type);
        return saver.save(is, ext);
    }
}
