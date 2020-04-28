package com.fykj.scaffold.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fykj.scaffold.security.business.domain.BackendUserDetail;
import com.fykj.scaffold.support.utils.SystemUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

/**
 * 通用填充类 适用于mybatis plus
 *
 * @author zhangzhi
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private static final String CREATE_DATE = "createDate";
    /**
     * 修改时间
     */
    private static final String UPDATE_DATE = "updateDate";
    /**
     * 创建者ID
     */
    private static final String CREATOR = "creator";

    /**
     * 修改者ID
     */
    private static final String UPDATER = "updater";

    @Override
    public void insertFill(MetaObject meta) {
        LocalDateTime now = LocalDateTime.now();
        String userId = userId();
        setInsertFieldValByName(CREATE_DATE, now, meta);
        setInsertFieldValByName(CREATOR, userId, meta);
        setInsertFieldValByName(UPDATE_DATE, now, meta);
        setInsertFieldValByName(UPDATER, userId, meta);
    }

    @Override
    public void updateFill(MetaObject meta) {
        setUpdateFieldValByName(UPDATE_DATE, LocalDateTime.now(), meta);
        setUpdateFieldValByName(UPDATER, userId(), meta);
    }

    /**
     * 获取当前用户ID
     */
    private String userId() {
        BackendUserDetail userDetail = SystemUtil.getUser();
        if (userDetail == null) {
            return "";
        }
        return userDetail.getId();
    }

}
