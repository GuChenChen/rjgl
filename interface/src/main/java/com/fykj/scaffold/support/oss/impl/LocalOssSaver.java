package com.fykj.scaffold.support.oss.impl;

import com.fykj.scaffold.security.business.domain.entity.Dict;
import com.fykj.scaffold.security.business.domain.entity.OssConfig;
import com.fykj.scaffold.security.business.service.IDictService;
import com.fykj.scaffold.security.business.service.IOssConfigService;
import com.fykj.scaffold.support.oss.IOssSaver;
import com.fykj.scaffold.support.oss.OssCons;
import constants.Mark;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import result.ResultCode;
import utils.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component(OssCons.OSS_IMPL_BEAN_PREFIX + OssCons.OSS_LOCAL_IMPL_BEAN)
public class LocalOssSaver implements IOssSaver {

    @Autowired
    private IOssConfigService ossConfigService;

    @Autowired
    private IDictService dictService;

    @Override
    public String save(InputStream is, String ext) {
        OssConfig config = ossConfigService.getConfig();
        checkConf(config);
        String virtualPath = getVirtualPath(config.getStorageLocation(), ext);
        String fileName = UUID.randomUUID().toString() + Mark.DOT + ext;
        File destFile = new File(config.getStorageLocation() + File.separator + virtualPath + File.separator + fileName);
        writeFile(destFile, is);
        virtualPath = virtualPath.replaceAll("\\\\", "/");
        return config.getUrl() + Mark.SLASH + virtualPath + Mark.SLASH + fileName;
    }

    /**
     * 获取文件分类所在文件夹
     *
     * @param ext 后缀
     * @return 文件夹名称
     */
    private String getClassifyFolderName(String ext) {
        return dictService.findFileTypeByValue(ext);
    }

    /**
     * 获取虚拟路径，并检查创建中间的文件夹
     *
     * @param storageLocation 根存储路径
     * @param ext             后缀
     * @return 虚拟路径
     */
    private String getVirtualPath(String storageLocation, String ext) {
        //获取文件分类所在文件夹，分类文件夹里每天再分一个文件夹,如果没有则先创建
        String virtualPath = getClassifyFolderName(ext) + File.separator + LocalDate.now().toString();
        String folderDir = storageLocation + File.separator + virtualPath;
        File dir = new File(folderDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return virtualPath;
    }

    /**
     * 写文件
     *
     * @param destFile 目标文件夹
     * @param is       输入流
     */
    private void writeFile(File destFile, InputStream is) {
        try (FileOutputStream fos = new FileOutputStream(destFile)) {
            FileCopyUtils.copy(is, fos);
        } catch (IOException e) {
            log.error("文件保存失败io异常", e);
            throw new BusinessException(ResultCode.ERROR, "文件保存失败io异常");
        }
    }

    /**
     * 校验配置是否完善
     *
     * @param config 配置
     */
    private void checkConf(OssConfig config) {
        if (config == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "无OSS配置，请先初始化OSS配置");
        }
        if (StringUtil.isEmpty(config.getUrl()) || StringUtil.isEmpty(config.getStorageLocation())) {
            throw new BusinessException(ResultCode.NOT_VALID, "本地存储的OSS配置不完整，请完善后再试");
        }
    }
}
