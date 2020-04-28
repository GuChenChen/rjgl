package com.fykj.scaffold.security.oauth2.filter;

import com.fykj.scaffold.security.business.domain.dto.ResourceOauthDto;
import com.fykj.scaffold.security.business.domain.entity.RoleResource;
import com.fykj.scaffold.security.business.service.IResourceService;
import com.fykj.scaffold.security.business.service.IRoleResourceService;
import constants.Mark;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import result.ResultCode;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author wangf
 */
@Slf4j
@Component
public class Oauth2FilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    @Autowired
    private IResourceService resourceService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ResourceOauthDto> list = resourceService.findResourceList();
        list.stream().forEach(roleResource -> {
            String path = roleResource.getPath();
            Collection<ConfigAttribute> configAttributes = resourceMap.computeIfAbsent(path, k -> new ArrayList<>());
            String roleId = roleResource.getRoleId();
            ConfigAttribute configAttribute = null;
            if(roleId == null){
                configAttribute = new SecurityConfig(ResultCode.FORBIDDEN.name());
            }else{
                configAttribute = new SecurityConfig(roleId);
            }
            configAttributes.add(configAttribute);
            resourceMap.put(path,configAttributes);
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //TODO 满足特定的条件可以不鉴权
        if(false){
            return null;
        }

        if (requestUrl.contains(Mark.QUESTION)) {
            requestUrl = requestUrl.substring(0, requestUrl.indexOf(Mark.QUESTION));
        }
        return resourceMap.get(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
