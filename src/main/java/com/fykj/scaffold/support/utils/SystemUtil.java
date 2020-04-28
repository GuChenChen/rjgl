package com.fykj.scaffold.support.utils;

import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author zhangzhi.
 * @date 2018/4/20.
 */
@Slf4j
public class SystemUtil {

    public static <T> T getBean(String beanName) {
        return SpringContextUtil.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return SpringContextUtil.getBean(requiredType);
    }

    public static HttpServletRequest getRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(it -> ((ServletRequestAttributes) it).getRequest())
                .orElse(null);
    }

    public static HttpServletResponse getResponse() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(it -> ((ServletRequestAttributes) it).getResponse())
                .orElse(null);
    }

    public static HttpSession getSession() {
        return Optional.ofNullable(getRequest())
                .map(HttpServletRequest::getSession)
                .orElse(null);
    }

    public static String getRequestURI() {
        return Optional.ofNullable(getRequest())
                .map(HttpServletRequest::getRequestURI)
                .orElse(null);
    }

    /**
     * 获取项目根路径
     *
     * @return 项目根路径
     */
    public static String getWebRootPath() {
        return Optional.ofNullable(getRequest())
                .map(httpServletRequest -> httpServletRequest.getServletContext().getRealPath("/"))
                .orElse(null);
    }

    /**
     * 获取当前token对应的用户信息
     *
     * @return {@link BackendUserDetail}
     */
    public static BackendUserDetail getUser() {
        BackendUserDetail backendUserDetail = null;
        try {
            if (SecurityContextHolder.getContext() == null) {
                return null;
            }
            backendUserDetail = (BackendUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            log.debug("exception: no login user");
        }
        return backendUserDetail;
    }

    /**
     * 获取配置文件中的值
     *
     * @param key 配置键
     * @return 配置值
     */
    public static String getProperty(String key) {
        Environment environment = getBean(Environment.class);
        return environment.getProperty(key);
    }

    /**
     * 获取客户端真实ip
     *
     * @param request 请求对象
     * @return 真实ip
     */
    public static String getClientIp(HttpServletRequest request) {
        if(request!=null){
            String ip = request.getHeader("x-forwarded-for");
            if (ipNotExists(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ipNotExists(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipNotExists(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }else {
            return null;
        }
    }

    public static String getClientIp() {
        return getClientIp(getRequest());
    }

    /**
     * 获取发起请求的浏览器名称
     */
    public static String getBrowserName(HttpServletRequest request) {
        if(request != null){
            return request.getHeader("User-Agent");
        }else {
            return null;
        }
    }

    public static String getBrowserName() {
        return getBrowserName(getRequest());
    }

    /**
     * 判断是否获取到当前的ip参数
     *
     * @param ip IP参数
     * @return 获取到：false/未获取：true
     */
    private static boolean ipNotExists(String ip) {
        return StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip);
    }

}
