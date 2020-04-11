package com.dmall.oms.service.impl.order.mapper.dto.tocomment;

import com.dmall.oms.service.impl.order.mapper.dto.CommentSkuDbDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: 评价分页响应实体
 * @author: created by hang.yu on 2020/4/9 22:20
 */
@Data
public class ToCommentDbDTO {

    /**
     * 子订单号
     */
    private Long subOrderId;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 订单的sku列表
     */
    private List<CommentSkuDbDTO> skuList;

}
