package com.fykj.scaffold.evaluation.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.CopInfo;
import com.fykj.scaffold.evaluation.domain.params.CopInfoParams;
import com.fykj.scaffold.evaluation.domain.vo.CopInfoVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * 服务类
 * @author gcc
 * @email ${email}
 * @date 2020-04-23 15:04:34
 */
public interface ICopInfoService extends IService<CopInfo> {

    /**
     * @Description 企业保存/更新
     * @Author gcc
     * @Date 2020/4/23 15:11
     * @Param CopInfoParams
     * @Return
     **/
    boolean saveOrUpdateCopInfo(CopInfoParams params);

    /**
     * @Description 企业列表分页查询
     * @Author gcc
     * @Date 2020/4/23 15:11
     * @Param IPage<CopInfoVo>
     * @Return
     **/
    IPage<CopInfoVo> findByPage(CopInfoParams params);
}

