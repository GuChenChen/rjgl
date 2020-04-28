package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.cms.domain.entity.FieldType;

/**
 * <p>
 * 字段类型服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
public interface IFieldTypeService extends IService<FieldType> {

    /**
     * 根据编号获取字段类型
     *
     * @param code 编号
     * @return 字段类型
     */
    FieldType getByCode(String code);

    /**
     * 根据编号查询名称
     *
     * @param type 类型编号
     * @return 类型名称
     */
    String getNameByCode(String type);

}
