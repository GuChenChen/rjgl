package com.fykj.scaffold.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.cms.domain.entity.AdviceField;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 咨询建议字段服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-11-21
 */
public interface IAdviceFieldService extends IService<AdviceField> {

    /**
     * 查询指定分类的采集项主键列表
     *
     * @param adviceId 指定分类主键
     * @return 采集项主键列表
     */
    List<String> findIdByAdviceId(Serializable adviceId);

    /**
     * 查询指定分类的采集项列表
     *
     * @param adviceId 指定分类主键
     * @return 采集项列表
     */
    List<AdviceField> findByAdviceId(Serializable adviceId);

    /**
     * 查询指定分类的采集项列表
     *
     * @param code 编号
     * @return 采集项列表
     */
    List<AdviceField> findByAdviceCode(String code);


    /**
     * 根据咨询id查询必填字段
     * @param adviceId
     * @return
     */
    List<IdTextVo> getByAdviceId(String adviceId);

}
