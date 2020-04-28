package com.fykj.scaffold.weixin.mp.controller;

import com.fykj.scaffold.weixin.mp.domain.dto.JsapiSignatureDto;
import exception.BusinessException;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wangf
 */
@ApiIgnore
@RestController
@RequestMapping("/wechat/signature")
public class SignatureController{

    @Autowired
    private WxMpService wxService;

    @ApiOperation(value = "创建调用jsapi时所需要的签名")
    @PostMapping("/createJsapi")
    public Result createJsapiSignature(@RequestBody @Validated JsapiSignatureDto jsapiSignatureDto) throws WxErrorException {
        String code = jsapiSignatureDto.getCode();
        try {
            if(wxService.switchover(code)){
                WxJsapiSignature jsapiSignature = wxService.createJsapiSignature(jsapiSignatureDto.getUrl());
                return new JsonResult<>(jsapiSignature);
            }
        } catch (WxErrorException e) {
            throw new BusinessException(ResultCode.ERROR,"创建签名失败");
        }
        return null;
    }
}
