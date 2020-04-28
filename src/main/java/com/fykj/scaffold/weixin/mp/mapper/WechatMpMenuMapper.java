package com.fykj.scaffold.weixin.mp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fykj.scaffold.weixin.mp.domain.entity.WechatMpMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuew
 * @since 2019-11-02
 */
public interface WechatMpMenuMapper extends BaseMapper<WechatMpMenu> {

    /**
     * 查询所有父级标签
     *
     * @param code 服务号code
     * @return 父类标签列表
     */
    List<WechatMpMenu> findFatherButtonByCode(String code);

    /**
     * 查询所有子级标签
     *
     * @param code     服务号code
     * @param parentId 父类id
     * @return 父类标签列表
     */
    List<WechatMpMenu> findSubButtonByCode(@Param("code") String code, @Param("parentId") String parentId);
}
