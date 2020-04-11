package com.dmall.oms.service.impl.order.es;

import lombok.Data;

/**
 * @description: 收货人信息
 * @author: created by hang.yu on 2020/4/11 21:02
 */
@Data
public class ReceiverDTO {

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人详细地址
     */
    private String receiverAddress;
}
