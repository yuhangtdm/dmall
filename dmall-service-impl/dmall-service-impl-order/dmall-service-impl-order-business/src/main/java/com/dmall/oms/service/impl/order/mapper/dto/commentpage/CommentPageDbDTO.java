package com.dmall.oms.service.impl.order.mapper.dto.commentpage;

import com.dmall.oms.service.impl.order.mapper.dto.CommentSkuDbDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: 评价分页响应实体
 * @author: created by hang.yu on 2020/4/9 22:20
 */
@Data
public class CommentPageDbDTO {

    /**
     * 子订单号
     */
    private Long subOrderId;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 子订单状态
     */
    private Integer subOrderStatus;

    /**
     * 评价状态
     */
    private String commentStatus;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 收货人姓名
     */
    private String receiverName;


    /**
     * 订单的sku列表
     */
    private List<CommentSkuDbDTO> skuList;

}
