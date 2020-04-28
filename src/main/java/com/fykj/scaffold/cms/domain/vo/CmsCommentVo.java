package com.fykj.scaffold.cms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Data;
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
@Accessors(chain = true)
public class CmsCommentVo {
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private String id;

    /**
     * 栏目id
     */
    private String categoryName;

    /**
     * 文章id
     */
    private String contentName;

    /**
     * 评论人名
     */
    private String name;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime commentTime;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 评论人ip
     */
    private String ip;

    /**
     * 是否回复
     */
    private Boolean reply;

    /**
     * 回复人id
     */
    private String replyPersonId;

    /**
     * 回复人
     */
    private String replyPerson;

    /**
     * 回复时间
     */
    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime replyTime;

    /**
     * 回复内容
     */
    private String replyContent;
}
