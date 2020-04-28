package com.fykj.scaffold.security.business.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fykj.scaffold.security.business.domain.dto.SysOssDto;
import com.fykj.scaffold.security.business.domain.entity.SysOss;
import com.fykj.scaffold.security.business.domain.params.OssParams;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传记录服务接口
 *
 * @author zhangzhi
 * @date 2019-03-04
 */
public interface ISysOssService extends IService<SysOss> {

    /**
     * 上传文件
     *
     * @param file       文件
     * @param serverCode 文件存储服务器编号
     * @param media 是否是媒体库
     * @param name 名称
     * @param labels 标签
     * @return 文件上传信息  {@link SysOss}
     */
    SysOss upload(MultipartFile file, String serverCode, boolean media, String name, String[] labels);

    /**
     * 根据文件路径查询文件上传记录
     *
     * @param path 文件相对路径
     * @return 文件上传信息 {@link SysOss}
     */
    SysOss getFileUpload(String path);

    /**
     * 后台分页查询信息
     *
     * @param page
     * @param ossParams
     * @return
     */
    IPage<SysOssDto> getListWithQuery(IPage<SysOssDto> page, OssParams ossParams);

}
