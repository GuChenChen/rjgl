package com.fykj.scaffold.security.oauth2.listener;

import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.service.ISysEvaluationUserService;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.SysLog;
import com.fykj.scaffold.security.business.service.ISysLogService;
import com.fykj.scaffold.security.business.service.IUserService;
import com.fykj.scaffold.support.utils.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

/**
 * 登录成功事件
 * @author wangf
 */
@Component
@Slf4j
public class MyAuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private ISysEvaluationUserService evaluationUserService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("client");
        if(!authorities.contains(grantedAuthority) && ("org.springframework.security.authentication.UsernamePasswordAuthenticationToken".equalsIgnoreCase(event.getSource().getClass().getName())||"com.fykj.scaffold.security.oauth2.sms.SmsCodeAuthenticationToken".equalsIgnoreCase(event.getSource().getClass().getName()))){
            User user= (User) authentication.getPrincipal();
            SysLog sysLog = new SysLog();
            sysLog.setUsername(user.getUsername());
            sysLog.setOperation("登录");
            sysLog.setMethod("login");
            sysLog.setTime(0L);
            sysLog.setIp(SystemUtil.getClientIp(SystemUtil.getRequest()));
            sysLogService.save(sysLog);
            setLastLoginDate(((BackendUserDetail)user).getId());
        }
    }

    private void setLastLoginDate(String userId){
        SysEvaluationUser user = evaluationUserService.getByUserId(userId);
        if(!ObjectUtils.isEmpty(user)){
            evaluationUserService.setLastLoginTime(user.getId());
        }
    }
}
