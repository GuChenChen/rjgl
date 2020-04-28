package com.fykj.scaffold.weixin.mp.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.support.utils.BeanUtil;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpAccount;
import com.fykj.scaffold.weixin.mp.mapper.WechatMpAccountMapper;
import com.fykj.scaffold.weixin.mp.service.IWechatMpAccountService;
import exception.BusinessException;
import org.springframework.stereotype.Service;
import result.ResultCode;
import utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangf
 * @since 2019-10-30
 */
@Service
public class WechatMpAccountServiceImpl extends BaseServiceImpl<WechatMpAccountMapper, WechatMpAccount> implements IWechatMpAccountService {

    //密文未修改标识
    private final static String NOT_MODIFY_FLAG = "notmodify";

    @Override
    public boolean updateByIdFromPage(WechatMpAccount account) {
        WechatMpAccount raw = getById(account.getId());
        if (raw == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "找不到资源");
        }
        boolean isExistCode = isExistCode(account);
        if (isExistCode) {
            throw new BusinessException(ResultCode.EXIST, "code已存在");
        }
        List<String> notModifyFields = new ArrayList<>();
        if (NOT_MODIFY_FLAG.equals(account.getAesKey())) {
            notModifyFields.add("aesKey");
        }
        if (NOT_MODIFY_FLAG.equals(account.getToken())) {
            notModifyFields.add("token");
        }
        if (NOT_MODIFY_FLAG.equals(account.getSecret())) {
            notModifyFields.add("secret");
        }
        BeanUtil.copyProperties(account, raw, notModifyFields.toArray(new String[0]));
        return updateById(raw);
    }

    @Override
    public boolean save(WechatMpAccount entity) {
        boolean isExistCode = isExistCode(entity);
        if (isExistCode) {
            throw new BusinessException(ResultCode.EXIST, "code已存在");
        }
        return super.save(entity);
    }

    /**
     * 校验code是否存在
     *
     * @param account
     * @return
     */
    private boolean isExistCode(WechatMpAccount account) {
        return lambdaQuery().eq(WechatMpAccount::getCode, account.getCode())
                .ne(StringUtil.isNotEmpty(account.getId()), WechatMpAccount::getId, account.getId()).count() > 0;
    }
}
