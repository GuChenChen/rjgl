package com.fykj.scaffold.support.oss;

import java.io.InputStream;

public interface IOssSaver {

    /**
     * 将文件存到OSS上
     *
     * @param is  输入流
     * @param ext 文件后缀
     * @return
     */
    String save(InputStream is, String ext);
}
