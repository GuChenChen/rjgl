package com.fykj.scaffold.security.business.service.impl;

import com.fykj.scaffold.security.business.service.IFileService;
import com.fykj.scaffold.support.utils.ImageUtil;
import exception.BusinessException;
import org.springframework.stereotype.Service;
import result.ResultCode;

/**
 * 文件上传记录服务实现
 *
 * @author zhangzhi
 * @date 2019-03-04
 */
@Service
public class FileServiceImpl implements IFileService {

    @Override
    public String getFilePath(String path, int width, int height, boolean fix) {
        if (!ImageUtil.isImage(path)) {
            return path;
        }
        if (height == 0 && width == 0) {
            return path;
        }
        if (!fix) {
            if (height == 0 || width == 0) {
                throw new BusinessException(ResultCode.NOT_VALID, "图片缩放比例参数不全");
            }
            return ImageUtil.resize(path, width, height);
        }

        return ImageUtil.resizeFix(path, width, height);

    }
}
