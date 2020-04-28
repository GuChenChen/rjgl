package com.fykj.scaffold.config;

import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author wangf
 * @date 2019/3/19
 */
@Data
@Configuration
@EnableConfigurationProperties(YunPianProperties.class)
public class YunPianConfiguration {

    private YunPianProperties yunPianProperties;

    private static Map<String, YunPianProperties.MpConfig> myConfig = Maps.newHashMap();

    public static Map<String, YunPianProperties.MpConfig> getMyConfig() {
        return myConfig;
    }

    public YunPianConfiguration() {
    }

    @Autowired
    public YunPianConfiguration(YunPianProperties yunPianProperties) {
        this.yunPianProperties = yunPianProperties;
    }

    @PostConstruct
    public void initProperties(){
        final List<YunPianProperties.MpConfig> configs = this.yunPianProperties.getConfigs();

        if (configs == null) {
            throw new RuntimeException("大哥，拜托先注意相关配置，注意别配错了！");
        }

        myConfig=configs.stream().collect(Collectors.toMap(YunPianProperties.MpConfig::getCode, a->a,(k1, k2)->k1));
    }
}
