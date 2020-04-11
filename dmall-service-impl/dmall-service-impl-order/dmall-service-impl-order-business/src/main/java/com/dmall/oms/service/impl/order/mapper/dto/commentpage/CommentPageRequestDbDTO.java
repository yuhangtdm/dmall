package com.dmall.oms.service.impl.order.mapper.dto.commentpage;

import lombok.Data;

/**
 * @description: 评价分页请求实体
 * @author: created by hang.yu on 2020/4/11 22:04
 */
@Data
public class CommentPageRequestDbDTO {

    /**
     * 评价状态
     */
    private String commentStatus;

    /**
     * 订单创建人
     */
    private Long creator;
}
