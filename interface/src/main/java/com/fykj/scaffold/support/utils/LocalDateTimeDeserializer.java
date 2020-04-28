package com.fykj.scaffold.support.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import utils.LocalDateTimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 通过注解，将前端传过来的时间格式化
 *
 * @author wangf
 * @date 2019/2/19
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String result = StringDeserializer.instance.deserialize(jsonParser, deserializationContext);
        return LocalDateTimeUtil.parse(result);
    }
}
