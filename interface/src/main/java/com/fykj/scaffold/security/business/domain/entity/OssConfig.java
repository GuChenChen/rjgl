package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

/**
 * <p>
 *
 * </p>
 *
 * @author wangf
 * @since 2019-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_oss_config")
public class OssConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 本地存储url
     */
    @TableField("url")
    @Size(max = 100)
    private String url;

    /**
     * 本地存储存储路径
     */
    @TableField("storage_location")
    @Size(max = 100)
    private String storageLocation;

    /**
     * 七牛云域名
     */
    @TableField("qn_domain")
    @Size(max = 100)
    private String qnDomain;

    /**
     * 七牛云AccessKey
     */
    @TableField("qn_access_key")
    @Size(max = 100)
    private String qnAccessKey;

    /**
     * 七牛云密匙
     */
    @TableField("qn_secret_key")
    @Size(max = 100)
    private String qnSecretKey;

    /**
     * 七牛云空间
     */
    @TableField("qn_bucket_name")
    @Size(max = 100)
    private String qnBucketName;

}
