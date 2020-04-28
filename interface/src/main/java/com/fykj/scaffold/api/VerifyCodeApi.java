package com.fykj.scaffold.api;

import com.fykj.scaffold.config.YunPianConfiguration;
import com.fykj.scaffold.config.YunPianProperties;
import com.fykj.scaffold.security.business.domain.dto.MessageDto;
import com.fykj.scaffold.security.business.domain.dto.ValidCodeDto;
import com.fykj.scaffold.security.business.service.ISendSmsService;
import com.fykj.scaffold.support.utils.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import result.Result;
import result.ResultCode;
import utils.GenerateCodeUtil;

import java.math.BigDecimal;

/**
 *  云片网
 * @author wangming
 * @date 2019/8/21
 */
@Controller
@RequestMapping(value = "/verify")
@Slf4j
@Api(tags = "短信验证码")
public class VerifyCodeApi {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ISendSmsService sendSmsService;

    @ApiOperation("发送验证码接口")
    @RequestMapping(value = "/verifyCode",method = RequestMethod.POST)
    @ResponseBody
    public Result sendVerifyCode(@RequestBody @Validated({MessageDto.VerifyCode.class}) MessageDto messageDto){
        //通过code获取配置文件中的短信模板
        YunPianProperties.MpConfig mpConfig = YunPianConfiguration.getMyConfig().get(messageDto.getCode());

        if (mpConfig == null) {
            return new Result(ResultCode.NOT_FOUND.code(), "未找到对应的模板");
        }

        //分钟转秒
        Long expireTime = new BigDecimal(messageDto.getExpireTime())
                .multiply(new BigDecimal(60)).longValue();

        String code = GenerateCodeUtil.identifyingCode();
        String text = String.format(mpConfig.getTemplate(), code,String.valueOf(messageDto.getExpireTime()));


        Result result = sendSmsService.sendSms(mpConfig.getApikey(),messageDto.getCode(),messageDto.getMobile(),text);

        if(result.isSuccess()){
            redisService.set(messageDto.getMobile() + messageDto.getCode(),code,expireTime);
        }
        return result;

    }
    @ApiOperation("验证验证码接口")
    @RequestMapping(value = "/validCode",method = RequestMethod.POST)
    @ResponseBody
    public Result validCode(@RequestBody @Validated({ValidCodeDto.Other.class}) ValidCodeDto validCodeDto){
        String key = validCodeDto.getMobile() + validCodeDto.getCode();
        if(redisService.exists(key)){
            String data = redisService.get(key);
            if(validCodeDto.getVerifyCode().equalsIgnoreCase(data)){
                redisService.remove(key);
                return new Result();
            }else{
                return new Result(ResultCode.ERROR.code(),"验证码错误");
            }
        }else{
            return new Result(ResultCode.ERROR.code(),"验证码错误");
        }

    }
}
