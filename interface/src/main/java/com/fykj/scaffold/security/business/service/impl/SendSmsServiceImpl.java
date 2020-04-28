package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.security.business.service.ISendSmsService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import result.Result;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-08-21
 */
@Slf4j
@Service
public class SendSmsServiceImpl implements ISendSmsService {
//
//    @Autowired
//    private ISendSmsHistoryService smsHistoryService;

    @Override
    public Result sendSms(String apiKey, String code, String mobile, String context) {
        //初始化client
        YunpianClient client = new YunpianClient(apiKey).init();

        //封装发送的参数
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TEXT, context);
        //发送短信
        com.yunpian.sdk.model.Result<SmsSingleSend> r = client.sms().single_send(param);
        //最后释放client
        client.close();

//        if(r.isSucc()){
//            smsHistoryService.saveHistory(mobile,code,context);
//            return new Result();
//        }
        log.error("短信异常",r.getThrowable());
        return new Result(r.getCode(),r.getMsg());
    }
}
