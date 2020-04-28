package com.fykj.scaffold.support.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhi
 */
@Component
public class DictCacheClient {

    @Autowired
    private RedisService service;

    public static final String DICT_KEY = "dict-cache";

    public void addDict(String code, String value) {
        service.hmSet(DICT_KEY, code, value);
    }

    public String getDict(String code) {
        return service.hmGet(DICT_KEY, code);
    }

    public void deleteDict(String code) {
        service.hmDel(DICT_KEY, code);
    }

    public void deleteAll() {
        service.remove(DICT_KEY);
    }

}
