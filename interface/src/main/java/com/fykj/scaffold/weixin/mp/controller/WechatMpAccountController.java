package com.fykj.scaffold.weixin.mp.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.weixin.mp.config.WxMpMyConfigImpl;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpAccount;
import com.fykj.scaffold.weixin.mp.domain.params.WechatMpAccountParams;
import com.fykj.scaffold.weixin.mp.service.IWechatMpAccountService;
import com.fykj.scaffold.weixin.mp.service.impl.WechatMpAccountServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangf
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/admin/wechat/account")
@Api(tags = "服务号管理")
public class WechatMpAccountController extends BaseController<WechatMpAccountServiceImpl, WechatMpAccount, WechatMpAccountParams> {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private IWechatMpAccountService wechatMpAccountService;

    @ApiOperation("更新java缓存中的服务号配置，每次修改完服务号配置，点击刷新即可，避免频繁的调用数据库")
    @GetMapping("/refresh")
    public Result updateCache() {
        List<WechatMpAccount> configs = wechatMpAccountService.list();
        if (configs != null) {
            wxService.setMultiConfigStorages(configs
                    .stream().map(a -> {
                        WxMpMyConfigImpl configStorage = new WxMpMyConfigImpl();
                        configStorage.setSecret(a.getSecret());
                        configStorage.setAppId(a.getAppId());
                        configStorage.setToken(a.getToken());
                        configStorage.setAesKey(a.getAesKey());
                        configStorage.setCode(a.getCode());
                        return configStorage;
                    }).collect(Collectors.toMap(WxMpMyConfigImpl::getCode, a -> a, (o, n) -> o)));
        }
        return OK;
    }

    @ApiOperation("分页查询")
    @PostMapping(value = "/list")
    public Result page(@RequestBody WechatMpAccountParams params) {
        return super.list(params);
    }

    @Override
    public Result get(String id) {
        WechatMpAccount account = baseService.getById(id);
        account.setAesKey("");
        account.setSecret("");
        account.setToken("");
        return new JsonResult<>(account);
    }

    @Override
    public Result update(@RequestBody WechatMpAccount entity) {
        if (baseService.updateByIdFromPage(entity)) {
            return OK;
        }
        return new Result(ResultCode.DATA_EXPIRED);
    }
}
