package com.fykj.scaffold.config;

import com.fykj.scaffold.support.utils.JsonUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author admin
 * @date 2019/3/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "yunpian")
public class YunPianProperties {

    private List<MpConfig> configs;

    @Data
    public static class MpConfig {
        /**
         * 云片网apikey
         */
        private String apikey;

        /**
         *
         */
        private String code;

        /**
         * 短消息模板
         */
        private String template;

    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
