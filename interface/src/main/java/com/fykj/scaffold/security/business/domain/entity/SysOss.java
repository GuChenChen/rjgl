package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author zhangzhi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oss")
public class SysOss extends BaseEntity {
    private static final long serialVersionUID = -3258197726335596410L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "原文件名")
    private String fileName;

    /**
     * 文件扩展后缀（不带点）
     */
    @ApiModelProperty(value = "文件后缀名（不带点）")
    private String fileExt;

    /**
     * 文件访问路径
     */
    @ApiModelProperty(value = "文件访问路径")
    private String path;

    /**
     * 文件大小（Byte）
     */
    @ApiModelProperty(value = "文件大小（Byte）")
    @TableField("file_size")
    private Long fileSize;

    /**
     * 文件分类
     */
    @ApiModelProperty(value = "文件分类（document:文档/video:视频/audio:音频/picture:图片）")
    private String type;

    @ApiModelProperty(value = "文件分类（文档/视频/音频/图片）")
    @TableField(exist = false)
    private String typeText;

    /**
     * 存储服务器编码
     */
    @ApiModelProperty(value = "存储服务器编码（本地:LocalServer/七牛云:QiniuCloud/阿里云:AliCloud/腾讯云:QCloud）")
    private String serverCode;

    @ApiModelProperty(value = "存储服务器名称（本地/七牛云/阿里云/腾讯云）")
    @TableField(exist = false)
    private String serverText;

    /**
     * 是否是媒体库
     */
    @TableField("is_media")
    @ApiModelProperty(value = " 是否是媒体库")
    private Boolean media;

    /**
     * 标签
     */
    @TableField(exist = false)
    @ApiModelProperty(value = " 标签")
    private String[] labels;
}