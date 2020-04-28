package com.fykj.scaffold.security.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.entity.Dict;
import com.fykj.scaffold.security.business.domain.vo.DictVo;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典服务类
 * </p>
 *
 * @author zhangzhi
 * @since 2019-02-20
 */
public interface IDictService extends IService<Dict> {

    /**
     * 查询指定编码的数据字典对象
     *
     * @param code 指定字典编码
     * @return 字典对象
     */
    Dict getByCode(String code);

    /**
     * 查询指定类别code的数据字典列表
     *
     * @param code 数据字典类型编码
     * @return 数据字典列表
     */
    List<Dict> findByParentCode(String code);

    /**
     * 根据数据字典的code获取字典的名称
     * -- code是 {@link null} 或者空时，返回空字符串
     * -- 未找到对应code的字典对象时，返回入参code值
     *
     * @param code 字典code
     * @return 对应字典名称
     */
    String getNameByCode(String code);

    /**
     * 下拉列表获取
     *
     * @param type 下拉列表类型
     * @return 下拉列表
     */
    List<IdTextVo> idTextVoList(String type);

    /**
     * 获取所有顶级数据
     *
     * @return
     */
    List<IdTextVo> findTopDict();

    /**
     * code是否存在
     *
     * @param id
     * @param code
     * @return
     */
    boolean checkCodeExists(String id, String code);

    /**
     * 根据value模糊查询
     *
     * @param value
     * @return
     */
    String findFileTypeByValue(String value);

    /**
     * 获取Oss存储允许上传后缀列表
     *
     * @return 后缀列表
     */
    List<String> getOssExtList();


    /**
     * 获取所有数据字典
     *
     * @return
     */
    Map<String,String> findAllDict();

    /**
     * 获取所有
     * @return
     */
    List<DictVo> findAll();

    /**
     * 刷新缓存数据
     */
    void refreshDictCache();
}
