package com.dmall.oms.api.dto.commentpage.response;

import com.dmall.common.enums.YNEnum;
import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: 评价分页响应实体
 * @author: created by hang.yu on 2020/4/9 22:20
 */
@Data
@ApiModel(value = "CommentPageResponseDTO", description = "评价分页响应实体")
public class CommentPageResponseDTO {

    @ApiModelProperty(value = "子订单号", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "评价状态", position = 2)
    private YNEnum commentStatus;

    @ApiModelProperty(value = "下单时间", position = 3)
    private Date orderTime;

    @ApiModelProperty(value = "订单项列表", position = 4)
    private List<BuyerOrderItemDTO> skuList;

    @ApiModelProperty(value = "收货人名称", position = 5)
    private String receiverName;

}
