package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.SysEvaluationUser;
import com.fykj.scaffold.evaluation.domain.params.SysEvaluationUserParams;
import com.fykj.scaffold.evaluation.domain.vo.TestAccountListVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangf
 * @since 2020-02-25
 */
public interface ISysEvaluationUserService extends IService<SysEvaluationUser> {
    /**
     * 后台分页查询信息
     *
     * @param params
     * @return
     */
    IPage<TestAccountListVo> getListWithQuery(SysEvaluationUserParams params);

    /**
     * @Description 根据申请单位id获取申请单位信息
     * @Author lmy
     * @Date 2020/4/7 10:30
     * @Param
     * @Return
     **/
    TestAccountListVo getCompanyInfoById(String id);

    /**
     * 获取账户信息
     * @param id
     * @return
     */
    TestAccountListVo findDetail(String id);

    /**
     * 保存账号
     * @param vo
     * @return
     */
    boolean saveTestAccount(TestAccountListVo vo);

    /**
     * 修改账号 -- 目前只能修改单位
     * @param vo
     * @return
     */
    boolean updateTestAccount(TestAccountListVo vo);

    /**
     * 删除账号
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * @return
     */
    SysEvaluationUser getByLoginUser();

    /**
     * 设置最后登录时间
     * @param id
     */
    void setLastLoginTime(String id);

    /**
     * @param userId
     * @return
     *
     */
    SysEvaluationUser getByUserId(String userId);
}
