package com.fykj.scaffold.security.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fykj.scaffold.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("api_param_manage")
public class ApiParamManage extends BaseEntity {

    private static final long serialVersionUID = -6527396075370085423L;

    /**
     * 参数类型（string/int....)
     */
    @TableField("type")
    private String type;

    /**
     * 参数名
     */
    @TableField("name")
    private String name;

    /**
     * 参数所属接口id
     */
    @TableField("url_id")
    private String urlId;

    /**
     * 参数所属接口code
     */
    @TableField("url_code")
    private String urlCode;

    /**
     * 参数序号
     */
    @TableField("sort_num")
    private Integer sortNum;

    /**
     * 是否必填
     */
    @TableField("need")
    private Boolean need;
}
