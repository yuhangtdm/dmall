package com.dmall.oms.api.dto.deliverpage;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.SourceEnum;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description: 发货分页请求实体
 * @author: created by hang.yu on 2020/4/5 10:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeliverOrderPageRequestDTO", description = "发货分页请求实体")
public class DeliverOrderPageRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "子订单号", position = 2)
    private Long subOrderId;

    @ApiModelProperty(value = "订单来源", position = 3)
    @ValueInEnum(SourceEnum.class)
    private Integer source;

    @ApiModelProperty(value = "会员id", position = 4)
    private Long memberId;

    @ApiModelProperty(value = "子订单状态", position = 5)
    @ValueInEnum(SubOrderStatusEnum.class)
    private Integer subOrderStatus;

    @ApiModelProperty(value = "订单创建时间,默认传当天", position = 6)
    private Date createTime = new Date();

    @ApiModelProperty(value = "发货人id", position = 7)
    private Long deliverId;
}
