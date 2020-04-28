package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.dto.UserFieldValueDto;
import com.fykj.scaffold.cms.domain.entity.UserFieldValue;
import com.fykj.scaffold.cms.domain.vo.UserFieldValueVo;

/**
 * <p>
 * 用户字段值服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
public interface IUserFieldValueService extends IService<UserFieldValue> {

    boolean saveUserFieldValue(UserFieldValueDto userFieldValueDto);

    /**
     * 导出
     * @param adviceId
     * @return
     */
    UserFieldValueVo getDataByAdviceId(String adviceId);
}
