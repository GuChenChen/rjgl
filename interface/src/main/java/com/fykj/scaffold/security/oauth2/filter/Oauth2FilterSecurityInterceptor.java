package com.fykj.scaffold.security.oauth2.filter;

import com.fykj.scaffold.security.oauth2.access.Oauth2AccessDecisionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;

/**
 *  重写过滤器，拦截所有/admin/** 请求，主要负责web应用安全授权的工作。
 * @author wangf
 */
@Slf4j
public class Oauth2FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private Oauth2FilterInvocationSecurityMetadataSource securityMetadataSource;


    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public void setOauth2AccessDecisionManager(Oauth2AccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager newManager) {
        super.setAuthenticationManager(newManager);
    }

    public void setSecurityMetadataSource(Oauth2FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (log.isInfoEnabled()) {
            log.info("Oauth2FilterSecurityInterceptor init");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (log.isInfoEnabled()) {
            log.info("Oauth2FilterSecurityInterceptor doFilter");
        }
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        invoke(filterInvocation);
    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        // filterInvocation里面有一个被拦截的url
        // 里面调用 Oauth2AccessDecisionManager 的 getAttributes(Object object) 这个方法获取 filterInvocation 对应的所有权限
        // 再调用 Oauth2AccessDecisionManager 的 decide方法来校验用户的权限是否足够
        InterceptorStatusToken interceptorStatusToken = super.beforeInvocation(filterInvocation);
        try {
            // 执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(interceptorStatusToken, null);
        }
    }
}
