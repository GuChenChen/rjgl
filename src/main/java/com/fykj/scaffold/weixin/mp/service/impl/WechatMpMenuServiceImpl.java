package com.fykj.scaffold.weixin.mp.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.support.conns.WxMenuTypeCons;
import com.fykj.scaffold.support.utils.BeanUtil;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpMenu;
import com.fykj.scaffold.weixin.mp.mapper.WechatMpMenuMapper;
import com.fykj.scaffold.weixin.mp.service.IWechatMpMenuService;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxMpErrorMsgEnum;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import result.ResultCode;
import utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xuew
 * @since 2019-11-02
 */
@Service
@Slf4j
public class WechatMpMenuServiceImpl extends BaseServiceImpl<WechatMpMenuMapper, WechatMpMenu> implements IWechatMpMenuService {

    @Autowired
    private WxMpService wxMpService;

    @Override
    public List<WechatMpMenu> getByCode(String code) {
        return lambdaQuery().eq(WechatMpMenu::getMpCode, code).orderByAsc(WechatMpMenu::getSequence).list();
    }

    @Override
    public boolean updateMenu(WechatMpMenu menu) {
        WechatMpMenu raw = baseMapper.selectById(menu.getId());
        if (raw == null) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        BeanUtils.copyProperties(menu, raw);
        //TODO 校验规则
        return updateById(raw);
    }

    @Override
    public boolean saveMenu(WechatMpMenu menu) {
        //判断是否是子节点，如果是子节点则将父节点转换为父菜单类型
        String parentId = menu.getParentId();
        if (StringUtil.isNotEmpty(parentId)) {
            WechatMpMenu parentMenu = lambdaQuery().eq(WechatMpMenu::getId, parentId).one();
            if (parentMenu != null) {
                parentMenu.setType(WxMenuTypeCons.FATHER_BTN_TYPE);
                updateById(parentMenu);
            }
        }
        return super.save(menu);
    }

    @Override
    public boolean removeById(Serializable id) {
        lambdaUpdate().eq(WechatMpMenu::getParentId, id).remove();
        return super.removeById(id);
    }

    @Override
    public void pushToWeChat(String code) {
        List<WechatMpMenu> fatherMenus = baseMapper.findFatherButtonByCode(code);
        if (CollectionUtils.isEmpty(fatherMenus)) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "请先设置菜单");
        }
        List<WxMenuButton> wxMenus = new ArrayList<>();
        fatherMenus.forEach(sysFaMenu -> {
            WxMenuButton wxFaMenu = new WxMenuButton();
            convertSysMenu2MpMenu(sysFaMenu, wxFaMenu);
            //查询子菜单
            List<WechatMpMenu> sysSubMenus = baseMapper.findSubButtonByCode(code, sysFaMenu.getId());
            if (WxMenuTypeCons.FATHER_BTN_TYPE.equals(sysFaMenu.getType()) && CollectionUtils.isEmpty(sysSubMenus)) {
                throw new BusinessException(ResultCode.ERROR, "菜单[" + sysFaMenu.getName() + "]下无子菜单，不能设置为父类菜单，请检查后再试");
            }
            List<WxMenuButton> wxSubMenuList = new ArrayList<>();
            sysSubMenus.forEach(sysSubMenu -> {
                WxMenuButton wxSubBtn = new WxMenuButton();
                convertSysMenu2MpMenu(sysSubMenu, wxSubBtn);
                wxSubMenuList.add(wxSubBtn);
            });
            wxFaMenu.setSubButtons(wxSubMenuList);
            wxMenus.add(wxFaMenu);
        });
        try {
            WxMenu wxMenu = new WxMenu();
            wxMenu.setButtons(wxMenus);
            wxMpService.getMenuService().menuCreate(wxMenu);
        } catch (WxErrorException e) {
            log.error("生成菜单失败了", e);
            throw new BusinessException(ResultCode.ERROR, "菜单配置失败" +
                    WxMpErrorMsgEnum.findMsgByCode(e.getError().getErrorCode()) + "\n 腾讯返回的错误：" + e.getError());
        }
    }

    @Override
    public void syncFromWeChat(String code) {
        try {
            WxMpMenu wxMenu = wxMpService.switchoverTo(code).getMenuService().menuGet();
            lambdaUpdate().eq(WechatMpMenu::getMpCode, code).remove();
            List<WxMenuButton> buttons = wxMenu.getMenu().getButtons();
            int seq = 1;
            for (WxMenuButton item : buttons) {
                WechatMpMenu entity = new WechatMpMenu();
                entity.setMpCode(code);
                convertMpMenu2SysMenu(item, entity);
                entity.setSequence(seq++);
                //有子菜单则设为父类型
                if (!CollectionUtils.isEmpty(item.getSubButtons())) {
                    entity.setType(WxMenuTypeCons.FATHER_BTN_TYPE);
                }
                save(entity);
                for (WxMenuButton subButton : item.getSubButtons()) {
                    WechatMpMenu subEntity = new WechatMpMenu();
                    subEntity.setMpCode(code);
                    convertMpMenu2SysMenu(subButton, subEntity);
                    subEntity.setParentId(entity.getId());
                    subEntity.setSequence(seq++);
                    save(subEntity);
                }
            }
        } catch (WxErrorException e) {
            log.error("同步菜单失败", e);
            throw new BusinessException(ResultCode.ERROR, "菜单配置失败" +
                    WxMpErrorMsgEnum.findMsgByCode(e.getError().getErrorCode()) + "\n 腾讯返回的错误：" + e.getError());
        }
    }

    private void convertSysMenu2MpMenu(WechatMpMenu sysMenu, WxMenuButton wxMpMenu) {
        BeanUtil.copyProperties(sysMenu, wxMpMenu);
        if (WxMenuTypeCons.FATHER_BTN_TYPE.equals(sysMenu.getType())) {
            wxMpMenu.setType(null);
        }
        wxMpMenu.setKey(sysMenu.getWxKey());
    }

    private void convertMpMenu2SysMenu(WxMenuButton wxMpMenu, WechatMpMenu sysMenu) {
        BeanUtil.copyProperties(wxMpMenu, sysMenu);
        sysMenu.setWxKey(wxMpMenu.getKey());
    }
}
