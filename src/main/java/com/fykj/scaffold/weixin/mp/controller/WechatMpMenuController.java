package com.fykj.scaffold.weixin.mp.controller;


import com.fykj.scaffold.base.BaseController;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpMenu;
import com.fykj.scaffold.weixin.mp.service.impl.WechatMpMenuServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.JsonResult;
import result.Result;
import result.ResultCode;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuew
 * @since 2019-11-02
 */
@RestController
@RequestMapping("/admin/wechat/menu")
public class WechatMpMenuController extends BaseController<WechatMpMenuServiceImpl, WechatMpMenu, BaseParams> {

    @Autowired
    private IDictService dictService;

    @ApiOperation("根据appCode获取它的菜单")
    @PostMapping(value = "/getMenusByCode")
    public JsonResult getWeChatMenuById(String code) {
        List<WechatMpMenu> list = baseService.getByCode(code);
        list.forEach(item -> item.setType(dictService.getNameByCode(item.getType())));
        return new JsonResult(list);
    }

    @Override
    public Result update(@RequestBody WechatMpMenu entity) {
        if (!baseService.updateMenu(entity)) {
            return new Result(ResultCode.DATA_EXPIRED);
        }
        return OK;
    }

    @Override
    public Result save(@RequestBody WechatMpMenu entity) {
        if (!baseService.saveMenu(entity)) {
            return new Result(ResultCode.DATA_EXPIRED);
        }
        return OK;
    }

    @ApiOperation("推送到腾讯服务器")
    @PostMapping(value = "/pushToWeChat")
    public Result pushToWeChat(String code) {
        baseService.pushToWeChat(code);
        return OK;
    }

    @ApiOperation("从腾讯服务器同步")
    @PostMapping(value = "/syncFromWeChat")
    public Result syncFromWeChat(String code) {
        baseService.syncFromWeChat(code);
        return OK;
    }
}
