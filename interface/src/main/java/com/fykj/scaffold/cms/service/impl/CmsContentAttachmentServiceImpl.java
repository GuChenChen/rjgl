package com.fykj.scaffold.cms.service.impl;

import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.cms.domain.entity.CmsContentAttachment;
import com.fykj.scaffold.cms.domain.vo.AttachVo;
import com.fykj.scaffold.cms.mapper.CmsContentAttachmentMapper;
import com.fykj.scaffold.cms.service.ICmsContentAttachmentService;
import com.fykj.scaffold.security.business.domain.entity.OssConfig;
import com.fykj.scaffold.security.business.domain.vo.IdTextVo;
import com.fykj.scaffold.security.business.service.IOssConfigService;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.ResultCode;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangm
 * @since 2019-10-30
 */
@Service
public class CmsContentAttachmentServiceImpl extends BaseServiceImpl<CmsContentAttachmentMapper, CmsContentAttachment> implements ICmsContentAttachmentService {

    @Autowired
    private IOssConfigService iOssConfigService;

    @Override
    public boolean saveAttach(AttachVo vo) {
        List<IdTextVo> filePaths = vo.getFilePaths();
        for (IdTextVo filePath : filePaths) {
            CmsContentAttachment attachment = new CmsContentAttachment();
            attachment.setContentId(vo.getContentId());
            attachment.setName(vo.getName());
            attachment.setFileName(filePath.getId());
            attachment.setFilePath(filePath.getText());
            attachment.setRemark(vo.getRemark());
            save(attachment);
        }
        return true;
    }

    @Override
    public boolean deleteByIds(List<String> idList){
        for (String id : idList) {
            CmsContentAttachment attachment = getById(id);
            OssConfig ossConfig = iOssConfigService.getConfig();
            String filePath = attachment.getFilePath().replace("/vpath/data/",ossConfig.getStorageLocation());
            super.removeById(id);
            File tempFile = new File(filePath);
            boolean result = tempFile.delete();
            if(!result){
                throw new BusinessException(ResultCode.FAIL,"删除文件失败！");
            }
        }
        return true;
    }

    @Override
    public List<CmsContentAttachment> findListByContentId(String contentId) {
        return lambdaQuery().eq(CmsContentAttachment::getContentId,contentId).orderByAsc(CmsContentAttachment::getCreateDate).list();
    }
}
