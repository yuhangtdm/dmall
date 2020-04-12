package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.oms.api.enums.SplitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 拆单响应实体
 * @author: created by hang.yu on 2020/4/6 11:33
 */
@Data
@ApiModel(value = "SplitOrderDTO", description = "拆单响应实体")
public class SplitOrderDTO {

    @ApiModelProperty(value = "订单拆分情况", position = 1)
    private SplitEnum split;

    @ApiModelProperty(value = "拆单原因", position = 2)
    private String splitReason;

    @ApiModelProperty(value = "拆单人员", position = 3)
    private Long splitPerson;

    @ApiModelProperty(value = "子订单列表", position = 4)
    private List<SubOrderDTO> subOrderList;
}
