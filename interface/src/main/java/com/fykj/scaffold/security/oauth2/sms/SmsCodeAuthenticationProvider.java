package com.fykj.scaffold.security.oauth2.sms;

import com.fykj.scaffold.security.oauth2.access.MyUserDetailsServiceImpl;
import com.fykj.scaffold.support.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private MyUserDetailsServiceImpl myUserDetailsService;

    @Autowired
    private RedisService redisService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        UserDetails user = myUserDetailsService.loadUserByUsername(mobile);
        String key = mobile + "yqtjpt";
        if(redisService.exists(key)){
            String data = redisService.get(key);
            if(((SmsCodeAuthenticationToken) authentication).getCode().equalsIgnoreCase(data)){
                redisService.remove(key);
            }else{
                throw new InvalidGrantException("验证码错误");
            }
        }else{
            throw new InvalidGrantException("验证码错误");
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    public void setUserServiceDetail(MyUserDetailsServiceImpl userServiceDetail) {
        this.myUserDetailsService = userServiceDetail;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
