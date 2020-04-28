package com.fykj.scaffold.evaluation.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author yangx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplyNoteSumByApplyTimeVo extends ApplyNoteVo{

    private Integer backTimes;

    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime receiveTime;

    private String receiveTimeText;

    private String amountAllReceived;

    @JsonFormat(pattern = Cons.DATETIME_FORMAT)
    private LocalDateTime testCompleteTime;

    private String testCompleteTimeText;

    private String time;

    private String score;
}
