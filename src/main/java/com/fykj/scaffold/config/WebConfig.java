package com.fykj.scaffold.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangzhi
 */
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void configurePathMatch(PathMatchConfigurer matcher) {
//        matcher.setUseRegisteredSuffixPatternMatch(true);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        IOssConfigService service = SystemUtil.getBean(IOssConfigService.class);
//        OssConfig conf = service.getConfig();
//        if (conf != null) {
//            String from = conf.getUrl() + "/**";
//            String to = "file:" + conf.getStorageLocation() + File.separator;
//            log.info("发现oss配置将：{}，映射到本地目录：{}", from, to);
//            //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
//            registry.addResourceHandler(from).addResourceLocations(to);
//        } else {
//            log.warn("oss未配置，系统无法映射本地存储虚拟路径");
//        }
//    }

}
