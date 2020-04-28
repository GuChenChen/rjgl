package com.fykj.scaffold.security.business.controller;


import com.alibaba.fastjson.JSON;
import com.fykj.scaffold.security.business.domain.dto.OAuth2ErrorDto;
import com.fykj.scaffold.security.business.domain.dto.OAuth2RequestDto;
import com.fykj.scaffold.security.business.domain.dto.Oauth2RefreshTokenDto;
import com.fykj.scaffold.security.business.service.IUserService;
import com.fykj.scaffold.support.utils.SystemUtil;
import exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import result.JsonResult;
import result.Result;
import result.ResultCode;
import utils.StringUtil;

@RestController
@Api(tags = "/login")
@Slf4j
public class LoginController {

    @Autowired
    private ClientCredentialsResourceDetails clientCredentialsResourceDetails;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private IUserService userService;

    @ApiOperation("用户登录")
    @PostMapping(value = "/fykj/login")
    public Result login(@RequestBody @Validated OAuth2RequestDto dto) {
        StringBuilder uri=new StringBuilder(clientCredentialsResourceDetails.getAccessTokenUri());
        if(dto.getLoginType().equals("password")){
            uri.append("?").append("username=").append(dto.getUsername())
                    .append("&password=").append(dto.getPassword())
                    .append("&grant_type=").append(clientCredentialsResourceDetails.getGrantType())
                    .append("&scope=").append(String.join("",clientCredentialsResourceDetails.getScope()))
                    .append("&client_id=").append(clientCredentialsResourceDetails.getClientId())
                    .append("&client_secret=").append(clientCredentialsResourceDetails.getClientSecret());
            return oauth(uri);
        }else{
            uri.append("?").append("mobile=").append(dto.getUsername())
                    .append("&smscode=").append(dto.getPassword())
                    .append("&grant_type=").append("sms_code")
                    .append("&scope=").append(String.join("",clientCredentialsResourceDetails.getScope()))
                    .append("&client_id=").append(clientCredentialsResourceDetails.getClientId())
                    .append("&client_secret=").append(clientCredentialsResourceDetails.getClientSecret());
            if(!userService.getByMobileAndSet(dto.getUsername(),dto.getEnterpriseName())){
                Result result = oauth(uri);
                result.setMsg("已为您升级为管理员，可切换为管理员登录，用户名为手机号，初始密码为123456");
                return result;
            }
            return oauth(uri);
        }
    }

    @ApiOperation("用户登录")
    @PostMapping(value = "/fykj/refresh_token")
    public Result refreshToken(@RequestBody @Validated Oauth2RefreshTokenDto dto) {
        StringBuilder uri=new StringBuilder(clientCredentialsResourceDetails.getAccessTokenUri());
        uri.append("?")
                .append("&refresh_token=").append(dto.getRefreshToken())
                .append("&grant_type=").append("refresh_token")
                .append("&client_id=").append(clientCredentialsResourceDetails.getClientId())
                .append("&client_secret=").append(clientCredentialsResourceDetails.getClientSecret());
        return oauth(uri);
    }

    private Result oauth(StringBuilder uri){
        RestTemplate restTemplate = new RestTemplate();
        try {
            String tokenResult = restTemplate.getForObject(uri.toString(), String.class);
            return new JsonResult<>(JSON.parse(tokenResult));
        } catch (HttpClientErrorException e) {
            //把SpringSecurity OAuth2的错误转换成我们封装的错误
            OAuth2ErrorDto errorDto = JSON.parseObject(e.getResponseBodyAsString(), OAuth2ErrorDto.class);
            throw new BusinessException(ResultCode.BAD_REQUEST, errorDto.getError_description());
        }
    }

    @ApiOperation("退出登录")
    @GetMapping(value = "/admin/logout")
    public Result logout(@RequestHeader HttpHeaders headers) {
        String value=headers.getFirst("Authorization");
        if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
            String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
            int commaIndex = authHeaderValue.indexOf(',');
            if (commaIndex > 0) {
                authHeaderValue = authHeaderValue.substring(0, commaIndex);
            }
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(authHeaderValue);
            tokenStore.removeAccessToken(oAuth2AccessToken);
            return new Result();
        }
        return new Result(ResultCode.ERROR);
    }

}
