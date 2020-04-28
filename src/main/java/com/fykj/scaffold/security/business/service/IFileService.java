package com.fykj.scaffold.security.business.service;

/**
 * <p>
 * 文件处理服务类
 * </p>
 *
 * @author zhangzhi
 */
public interface IFileService {

    /**
     * 获取目标文件名称（包含路径）
     *
     * @param path   原文件路径
     * @param width  目标宽度
     * @param height 目标高度
     * @param fix    是否等比例(默认true)
     * @return 处理后的文件路径（包含名称）
     */
    String getFilePath(String path, int width, int height, boolean fix);

}
