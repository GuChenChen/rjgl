package com.fykj.scaffold.support.oss.impl;

import com.fykj.scaffold.security.business.domain.entity.OssConfig;
import com.fykj.scaffold.security.business.service.IOssConfigService;
import com.fykj.scaffold.support.oss.IOssSaver;
import com.fykj.scaffold.support.oss.OssCons;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import constants.Mark;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import result.ResultCode;
import utils.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component(OssCons.OSS_IMPL_BEAN_PREFIX + OssCons.OSS_QN_IMPL_BEAN)
@Slf4j
public class QnOssSaver implements IOssSaver {

    @Autowired
    private IOssConfigService ossConfigService;

    @Value("${virtual.path.temp}")
    private String tempDir;

    @Override
    public String save(InputStream is, String ext) {
        OssConfig config = ossConfigService.getConfig();
        checkConfig(config);
        UploadManager uploadManager = getUploadManager();
        String upToken = Auth.create(config.getQnAccessKey(), config.getQnSecretKey()).uploadToken(config.getQnBucketName());
        File tempFolder = new File(tempDir);
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        //先写入临时文件，然后上传七牛云
        File tempFile = new File(tempDir + File.separator + UUID.randomUUID().toString() + Mark.DOT + ext);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            FileCopyUtils.copy(is, fos);
            Response response = uploadManager.put(tempFile, UUID.randomUUID().toString() + Mark.DOT + ext, upToken);
            //解析上传成功的结果
            DefaultPutRet ret = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (!tempFile.delete()) {
                log.warn("临时文件:{}删除失败", tempFile.getName());
            }
            return config.getQnDomain() + Mark.SLASH + ret.key;
        } catch (IOException ex) {
            log.error("七牛云上传异常", ex);
            throw new BusinessException(ResultCode.ERROR, "七牛云上传有异常");
        }
    }

    private void checkConfig(OssConfig config) {
        if (config == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "无OSS配置，请先初始化OSS配置");
        }
        if (StringUtil.isEmpty(config.getQnAccessKey()) || StringUtil.isEmpty(config.getQnSecretKey()) || StringUtil.isEmpty(config.getQnBucketName()) || StringUtil.isEmpty(config.getQnDomain())) {
            throw new BusinessException(ResultCode.NOT_VALID, "七牛存储的OSS配置不完整，请完善后再试");
        }
    }

    private static UploadManager getUploadManager() {
        Configuration cfg = new Configuration(Zone.zone0());
        return new UploadManager(cfg);
    }
}
