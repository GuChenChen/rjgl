package com.fykj.scaffold.evaluation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.evaluation.domain.entity.Notice;
import com.fykj.scaffold.evaluation.domain.params.NoticeParams;

/**
 * 通知
 *
 * 服务类
 * @author wangming
 * @email ${email}
 * @date 2020-02-25 13:11:46
 */
public interface INoticeService extends IService<Notice> {

    /**
     * 校验编码唯一性
     *
     * @param id
     * @param code
     * @return
     */
    boolean checkCode(String id, String code);

    /**
     * 后台分页查询信息
     *
     * @param page 分页对象
     * @param params 参数
     * @return 分页结果
     */
    IPage<Notice> getListWithQuery(IPage<Notice> page, NoticeParams params);
}

