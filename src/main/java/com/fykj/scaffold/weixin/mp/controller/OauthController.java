package com.fykj.scaffold.weixin.mp.controller;

import com.fykj.scaffold.support.utils.Encrypt;
import com.fykj.scaffold.support.utils.SystemUtil;
import constants.Mark;
import exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import result.ResultCode;
import springfox.documentation.annotations.ApiIgnore;
import utils.LocalDateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**  测试url：  http://XXXX/oAuth/authorize?backUrl=https%3A%2F%2Fwww.baidu.com&code=a&scope=2
 * @author wangf
 */
@ApiIgnore
@Controller
@RequestMapping("/oAuth")
@Api(value = "微信授权")
@Slf4j
public class OauthController {

    @Autowired
    private WxMpService wxService;

    public static final String AUTH_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    public static final String CALL_BACK_URL = "%s/callBack?backUrl=%s";


    /**
     * @param backUrl
     * @param code 数据库配置的服务号唯一码
     * @param scope   是否是静默授权，2代表静默，默认是静默授权
     */
    @ApiOperation(value = "微信授权")
    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public void getAuthUrLToGetCode(@RequestParam(required = true) String backUrl, @RequestParam(required = true) String code, @RequestParam String scope) throws UnsupportedEncodingException {
        log.info("thred:{}",Thread.currentThread().getName());
        if (StringUtils.isEmpty(backUrl)) {
            return;
        }
        if (!wxService.switchover(code)) {
            throw new BusinessException(ResultCode.TOKEN_ERROR_CODE, "未配置相关的服务号");
        }
        if ("1".equalsIgnoreCase(scope)) {
            scope = "snsapi_userinfo";
        } else {
            scope = "snsapi_base";
        }
        StringBuffer requestURL = SystemUtil.getRequest().getRequestURL();
        String redirectUrl = String.format(CALL_BACK_URL, requestURL.substring(0, requestURL.lastIndexOf(Mark.SLASH)), Encrypt.base64Encode(backUrl, StandardCharsets.UTF_8.name()));
        String authUrl = String.format(AUTH_URI, wxService.getWxMpConfigStorage().getAppId(), URLEncoder.encode(redirectUrl, StandardCharsets.UTF_8.name()), scope, code);
        try {
            log.info("authUrl:{}" + authUrl);
            log.info("startDate:{}" + LocalDateTimeUtil.getNow());
            SystemUtil.getResponse().sendRedirect(authUrl);
        } catch (IOException e) {
            log.error("微信授权失败" + e.getMessage());
        }
    }

    @ApiIgnore
    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    public String callBack(HttpServletRequest req) throws WxErrorException, UnsupportedEncodingException {
        log.info("thred:{}",Thread.currentThread().getName());
        String code = req.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            return "";
        }
        String state = req.getParameter("state");
        log.info("state:{}",state);
        wxService.switchoverTo(state);
        String backUrl = Encrypt.base64Decode(req.getParameter("backUrl"), "UTF-8");
        log.info("backUrl:{}", backUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        StringBuilder location = new StringBuilder("redirect:").append(backUrl);
        if (backUrl.contains(Mark.QUESTION)) {
            location.append("&openid=").append(openId);
        } else {
            location.append("?openid=").append(openId);
        }
        log.info("redirect:{}", location.toString());
        return location.toString();
    }


}
