package com.fykj.scaffold.evaluation.controller;

import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.evaluation.domain.entity.SmsSendRecord;
import com.fykj.scaffold.evaluation.domain.params.SmsSendRecordParams;
import com.fykj.scaffold.evaluation.service.impl.SmsSendRecordServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * 前端控制器
 * @author wangming
 * @date 2020-04-07 14:26:27
 */
@RestController
@RequestMapping("/admin/evaluation/sms")
@Api(tags = "短信发送记录接口")
public class SmsSendRecordController extends BaseController<SmsSendRecordServiceImpl, SmsSendRecord,SmsSendRecordParams> {

}
