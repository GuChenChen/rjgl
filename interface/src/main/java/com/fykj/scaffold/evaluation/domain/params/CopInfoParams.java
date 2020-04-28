package com.fykj.scaffold.evaluation.domain.params;

import com.fykj.scaffold.base.BaseParams;
import com.fykj.scaffold.support.wrapper.annotation.MatchType;
import com.fykj.scaffold.support.wrapper.enums.QueryType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 
 * 查询参数
 *
 * @author gcc
 * @email ${email}
 * @date 2020-04-23 15:04:34
 */
@Data
@NoArgsConstructor
public class CopInfoParams extends BaseParams {

    @MatchType(value = QueryType.EQ, fieldName = {"search"})
    @ApiModelProperty("搜索,模糊查询")
    private String search;

    @MatchType(value = QueryType.EQ, fieldName = {"viewTimeStart"})
    @ApiModelProperty("查看时间(开始),模糊查询")
    private String viewTimeStart;


    @MatchType(value = QueryType.EQ, fieldName = {"viewTimeEnd"})
    @ApiModelProperty("查看时间(结束),模糊查询")
    private String viewTimeEnd;

    private String id;
    /**
     * 企业统一社会信用
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_gb_code"})
    @ApiModelProperty(value = "企业统一社会信用")
    private String copGbCode;

    /**
     * 企业机构名称
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_name"})
    @ApiModelProperty(value = "企业机构名称")
    private String copName;

    /**
     * 企业地址
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_addr"})
    @ApiModelProperty(value = "企业地址")
    private String copAddr;

    /**
     * 营业场所
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_palce"})
    @ApiModelProperty(value = "营业场所")
    private String copPalce;

    /**
     * 法定代表人
     */
    @MatchType(value = QueryType.EQ, fieldName = {"legal_person"})
    @ApiModelProperty(value = "法定代表人")
    private String legalPerson;

    /**
     * 法定代表人身份证号码
     */
    @MatchType(value = QueryType.EQ, fieldName = {"legal_person_id"})
    @ApiModelProperty(value = "法定代表人身份证号码")
    private String legalPersonId;

    /**
     * 注册资本（万元）
     */
    @MatchType(value = QueryType.EQ, fieldName = {"reg_capital"})
    @ApiModelProperty(value = "注册资本（万元）")
    private String regCapital;

    /**
     * 经济类型
     */
    @MatchType(value = QueryType.EQ, fieldName = {"economy"})
    @ApiModelProperty(value = "经济类型")
    private String economy;

    /**
     * 经营范围
     */
    @MatchType(value = QueryType.EQ, fieldName = {"scope"})
    @ApiModelProperty(value = "经营范围")
    private String scope;

    /**
     * 行业类型
     */
    @MatchType(value = QueryType.EQ, fieldName = {"industry"})
    @ApiModelProperty(value = "行业类型")
    private String industry;

    /**
     * 联系人
     */
    @MatchType(value = QueryType.EQ, fieldName = {"link_man"})
    @ApiModelProperty(value = "联系人")
    private String linkMan;

    /**
     * 联系人电话
     */
    @MatchType(value = QueryType.EQ, fieldName = {"link_phone"})
    @ApiModelProperty(value = "联系人电话")
    private String linkPhone;

    /**
     * 联系人EMAIL
     */
    @MatchType(value = QueryType.EQ, fieldName = {"link_email"})
    @ApiModelProperty(value = "联系人EMAIL")
    private String linkEmail;

    /**
     * 邮政编码
     */
    @MatchType(value = QueryType.EQ, fieldName = {"post_code"})
    @ApiModelProperty(value = "邮政编码")
    private String postCode;

    /**
     * 企业状态
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_state"})
    @ApiModelProperty(value = "企业状态")
    private String copState;

    /**
     * 企业类型
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_type"})
    @ApiModelProperty(value = "企业类型")
    private String copType;

    /**
     * 企业分类
     */
    @MatchType(value = QueryType.EQ, fieldName = {"cop_sort"})
    @ApiModelProperty(value = "企业分类")
    private String copSort;

    /**
     * 登记日期
     */
    @MatchType(value = QueryType.EQ, fieldName = {"reg_date"})
    @ApiModelProperty(value = "登记日期")
    private String regDate;
}
