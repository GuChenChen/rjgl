package com.fykj.scaffold.cms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.base.BaseEntity;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xuew
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 文章id
     */
    @TableField("content_id")
    private String contentId;

    /**
     * 评论人名
     */
    @TableField("name")
    private String name;

    /**
     * 评论时间
     */
    @TableField("comment_time")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime commentTime;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 评论人ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 是否回复
     */
    @TableField("is_reply")
    private Boolean reply;

    /**
     * 回复人id
     */
    @TableField("reply_person_id")
    private String replyPersonId;

    /**
     * 回复人
     */
    @TableField("reply_person")
    private String replyPerson;

    /**
     * 回复时间
     */
    @TableField("reply_time")
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime replyTime;

    /**
     * 回复内容
     */
    @TableField("reply_content")
    private String replyContent;


}
