package com.fykj.scaffold.evaluation.domain.vo.ApplyNoteSumByStatusVos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fykj.scaffold.support.conns.Cons;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @Description 测试申请统计（按状态）
* @Author yangx
* @Date 2020/4/8 8:56
**/
@Data
@AllArgsConstructor
public class ApplyNoteSumByStatusVo {

    private List<SumInfoVo> infos;
}
