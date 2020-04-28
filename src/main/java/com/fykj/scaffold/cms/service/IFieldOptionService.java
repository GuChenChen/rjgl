package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.FieldOption;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字段选项服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
public interface IFieldOptionService extends IService<FieldOption> {

    /**
     * 删除指定字段的全部选项
     *
     * @param fieldId 字段主键
     */
    void removeByFieldId(String fieldId);

    /**
     * 查询指定字段的全部选项
     *
     * @param fieldId 字段主键
     * @return 字段选项列表
     */
    List<FieldOption> findByFieldId(Serializable fieldId);

}
