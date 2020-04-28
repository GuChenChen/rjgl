package com.fykj.scaffold.swagger;

import com.fykj.scaffold.support.conns.Oauth2AuthorizeCons;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import constants.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangf 新增@Profile，开发和测试阶段开放swagger-ui，正式环境swagger-ui关闭
 * @date 2019/2/19
 */

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class Swagger2 {

    @Autowired
    private SwaggerProperties properties;

    @Bean(value = "admin")
    public Docket createRestAdmin() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(Oauth2AuthorizeCons.OAUTH2_TOKEN_HEADER).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("admin")
                .select()
                .apis(basePackage(properties.getPackageScanAdmin()))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    @Bean(value = "api")
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(Oauth2AuthorizeCons.OAUTH2_TOKEN_HEADER).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("api")
                .select()
                .apis(basePackage(properties.getPackageScanApi()))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private static Predicate<RequestHandler> basePackage(final String basePackage) {
        List<Predicate<RequestHandler>> resultList = new ArrayList<>();
        for (String strPackage : basePackage.split(Mark.COMMA)) {
            resultList.add(RequestHandlerSelectors.basePackage(strPackage));
        }
        return Predicates.or(resultList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .contact(new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail()))
                .version(properties.getVersion())
                .build();
    }

}
