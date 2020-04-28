package com.fykj.scaffold.security.oauth2.config;

import com.fykj.scaffold.security.oauth2.access.Oauth2AccessDecisionManager;
import com.fykj.scaffold.security.oauth2.error.CustomAccessDeniedHandler;
import com.fykj.scaffold.security.oauth2.error.ErrorAnalyzer;
import com.fykj.scaffold.security.oauth2.error.MyOAuth2AuthenticationEntryPoint;
import com.fykj.scaffold.security.oauth2.filter.Oauth2FilterInvocationSecurityMetadataSource;
import com.fykj.scaffold.security.oauth2.filter.Oauth2FilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author wangf
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "order";

    @Autowired
    AuthenticationManager manager;

    @Autowired
    Oauth2AccessDecisionManager accessDecisionManager;

    @Autowired
    Oauth2FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Bean
    OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint(){
        return new MyOAuth2AuthenticationEntryPoint();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    ErrorAnalyzer errorAnalyzer(){
        return new ErrorAnalyzer();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        resources.authenticationEntryPoint(oAuth2AuthenticationEntryPoint());
        resources.accessDeniedHandler(accessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                /**
                 *  因为ResourceServerConfigurerAdapter的优先级高于WebSecurityConfigurerAdapter
                 * 1.拦截所有带/admin/**前缀的请求
                 * 2.对拦截的请求全部做权限验证
                 * 如果需要在分，在authorizeRequests()后将anyrequest()替换成antMatchers（**）
                 *
                 * 如果authorizeRequests()在.antMatcher("/admin/**").前面，则说明，次过滤器会处理所有请求，WebSecurityConfigurerAdapter将无法正常使用
                 */
                .antMatcher("/admin/**").authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

        /**
         * 替换当前位置的过滤器
         */
        http.addFilterAfter(createApiAuthenticationFilter(), FilterSecurityInterceptor.class);
    }

    /**
     * API权限控制
     * 过滤器优先度在 FilterSecurityInterceptor 之后
     * spring-security 的默认过滤器列表见 https://docs.spring.io/spring-security/site/docs/5.0.0.M1/reference/htmlsingle/#ns-custom-filters
     *
     * @return
     */
    private Oauth2FilterSecurityInterceptor createApiAuthenticationFilter() {
        Oauth2FilterSecurityInterceptor interceptor = new Oauth2FilterSecurityInterceptor();
        interceptor.setAuthenticationManager(manager);
        interceptor.setAccessDecisionManager(accessDecisionManager);
        interceptor.setSecurityMetadataSource(securityMetadataSource);
        return interceptor;
    }
}
