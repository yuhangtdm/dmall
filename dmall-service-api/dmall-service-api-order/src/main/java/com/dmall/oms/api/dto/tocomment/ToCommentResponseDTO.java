package com.dmall.oms.api.dto.tocomment;

import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 评价页面响应实体
 * @author: created by hang.yu on 2020/4/9 22:20
 */
@Data
@ApiModel(value = "ToCommentResponseDTO", description = "评价页面响应实体")
public class ToCommentResponseDTO implements Serializable {

    private static final long serialVersionUID = -3936536425300730203L;

    @ApiModelProperty(value = "子订单号", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "下单时间", position = 2)
    private Date orderTime;

    @ApiModelProperty(value = "订单项列表", position = 3)
    private List<BuyerOrderItemDTO> skuList;

}
