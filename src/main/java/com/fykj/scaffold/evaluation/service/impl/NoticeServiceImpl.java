package com.fykj.scaffold.evaluation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.evaluation.domain.entity.Notice;
import com.fykj.scaffold.evaluation.domain.params.NoticeParams;
import com.fykj.scaffold.evaluation.mapper.NoticeMapper;
import com.fykj.scaffold.evaluation.service.INoticeService;
import com.fykj.scaffold.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import utils.StringUtil;


/**
 * 通知
 *
 * 服务实现类
 * @author wangming
 * @email ${email}
 * @date 2020-02-25 13:11:46
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public boolean checkCode(String id, String code) {
        return lambdaQuery().eq(Notice::getCode,code).ne(StringUtil.isNotEmpty(id),Notice::getId,id).count() > 0;
    }

    @Override
    public IPage<Notice> getListWithQuery(IPage<Notice> page, NoticeParams params) {
        return baseMapper.getListWithQuery(page, params);
    }
}