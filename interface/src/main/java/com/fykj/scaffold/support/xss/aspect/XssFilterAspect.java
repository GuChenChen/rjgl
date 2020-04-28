package com.fykj.scaffold.support.xss.aspect;

import com.fykj.scaffold.support.utils.XssFilterUtil;
import com.fykj.scaffold.support.xss.annotation.XssFilterAttribute;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import utils.ClassUtil;
import utils.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author ZC
 * @date: 2019/10/31
 */
@Aspect
@Component
public class XssFilterAspect {

    @Pointcut("execution(* com.fykj.scaffold.security.business.controller..*.*(..))||" +
            "execution(* com.fykj.scaffold.base..*.*(..))")
    public void xssParamPointCut(){
    }

    @Around("xssParamPointCut()")
    public Object handleHtml(ProceedingJoinPoint joinPoint)throws Throwable{
        MethodSignature signature = ((MethodSignature) joinPoint.getSignature());
        //获取参数值
        Object[] paramValues = joinPoint.getArgs();
        Arrays.stream(paramValues).filter(Objects::nonNull).forEach(it-> {
            Field[] fields = it.getClass().getDeclaredFields();
            for(Field field:fields){
                //对该注解下标记的字段进行处理
                if (field.getAnnotation(XssFilterAttribute.class)==null){
                    continue;
                }
                Object value = ClassUtil.getFieldValue(it, field);
                if(StringUtil.isNotEmpty(value)){
                    String cleanValue = XssFilterUtil.clean((value.toString()));
                    ClassUtil.setFieldValue(it,field,cleanValue);
                }
            }

        });
        return joinPoint.proceed(paramValues);
    }
}
