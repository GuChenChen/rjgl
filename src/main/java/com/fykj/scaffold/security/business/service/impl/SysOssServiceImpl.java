package com.fykj.scaffold.security.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.base.BaseServiceImpl;
import com.fykj.scaffold.security.business.domain.dto.SysOssDto;
import com.fykj.scaffold.security.business.domain.entity.OssLabel;
import com.fykj.scaffold.security.business.domain.entity.SysOss;
import com.fykj.scaffold.security.business.domain.params.OssParams;
import com.fykj.scaffold.security.business.mapper.SysOssMapper;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.security.business.service.IOssLabelService;
import com.fykj.scaffold.security.business.service.ISysOssService;
import com.fykj.scaffold.support.oss.OssSaveUtil;
import com.fykj.scaffold.support.utils.ImageUtil;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import result.ResultCode;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 文件上传记录服务实现
 *
 * @author zhangzhi
 * @date 2019-03-04
 */
@Service
public class SysOssServiceImpl extends BaseServiceImpl<SysOssMapper, SysOss> implements ISysOssService {

    @Autowired
    private IDictService dictService;

    @Autowired
    private IOssLabelService ossLabelService;

    @Override
    public SysOss upload(MultipartFile file, String serverCode, boolean media, String name, String[] labels) {
        SysOss oss = new SysOss();
        String filename = file.getOriginalFilename();
        String fileExt = ImageUtil.getExt(filename);
        List<String> extList = dictService.getOssExtList();
        if (!extList.contains(fileExt.toLowerCase())) {
            throw new BusinessException(ResultCode.NOT_VALID, "不支持的文件格式");
        }
        oss.setType(dictService.findFileTypeByValue(fileExt));
        oss.setFileName(filename);
        oss.setName(name);
        oss.setFileExt(fileExt);
        oss.setFileSize(file.getSize());
        oss.setPath(getPath(file, serverCode, fileExt));
        oss.setServerCode(serverCode);
        oss.setMedia(media);
        save(oss);
        if (labels !=null) {
            for (String label : labels) {
                OssLabel ossLabel = new OssLabel();
                ossLabel.setOssId(oss.getId());
                ossLabel.setLabelId(label);
                ossLabelService.save(ossLabel);
            }
        }
        return oss;
    }

    @Override
    public IPage<SysOss> page(BaseParams params) {
        return super.page(params).convert(this::convert);
    }

    @Override
    public SysOss getById(Serializable id) {
        return convert(super.getById(id));
    }

    private SysOss convert(SysOss sysOss) {
        sysOss.setTypeText(dictService.getNameByCode(sysOss.getType()));
        sysOss.setServerText(dictService.getNameByCode(sysOss.getServerCode()));
        List<String> labels = ossLabelService.getByOssId(sysOss.getId());
        sysOss.setLabels(labels.toArray(new String[labels.size()]));
        return sysOss;
    }

    /**
     * 获取文件访问路径
     *
     * @param file       上传文件兑现
     * @param serverCode 存储服务器编码
     * @param fileExt    文件扩展名
     * @return 文件访问路径
     */
    private String getPath(MultipartFile file, String serverCode, String fileExt) {
        try (InputStream in = file.getInputStream()) {
            return OssSaveUtil.save(in, serverCode, fileExt);
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL, "读取文件失败", e);
        }
    }

    @Override
    public SysOss getFileUpload(String path) {
        SysOss upload = lambdaQuery().eq(SysOss::getPath, path).one();
        if (upload == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "未找到对应的文件");
        }
        return upload;
    }

    @Override
    public IPage<SysOssDto> getListWithQuery(IPage<SysOssDto> page, OssParams ossParams) {
        return baseMapper.getListWithQuery(page,ossParams);
    }

}
