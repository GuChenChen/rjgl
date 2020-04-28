package com.fykj.scaffold.support.syslog.aspect;

import com.alibaba.fastjson.JSON;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.security.business.domain.entity.SysLog;
import com.fykj.scaffold.security.business.service.ISysLogService;
import com.fykj.scaffold.support.syslog.annotation.SysLogMethod;
import com.fykj.scaffold.support.utils.SystemUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ClassUtil;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.fykj.scaffold.support.syslog.annotation.SysLogMethod)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();


        SysLog sysLog = new SysLog();
        String entryName = getEntryName(joinPoint);
        SysLogMethod logMethod = method.getAnnotation(SysLogMethod.class);
        if (logMethod != null) {
            //注解上的描述
            sysLog.setOperation(entryName + ":" + logMethod.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            String params = JSON.toJSON(args).toString();
            sysLog.setParams(params);
        }


        //设置IP地址
        sysLog.setIp(SystemUtil.getClientIp());


        BackendUserDetail user = SystemUtil.getUser();
        if (user != null) {
            //用户名
            sysLog.setUsername(user.getUsername());
        }

        sysLog.setTime(time);
        sysLog.setCreateDate(LocalDateTime.now());
        //保存系统日志
        sysLogService.save(sysLog);
    }

    private String getEntryName(ProceedingJoinPoint joinPoint) {
        Class clazz = ClassUtil.invokeMethod(joinPoint.getTarget(), "getType", new Class[]{}, new Object[]{});
        return clazz.getSimpleName();
    }
}
