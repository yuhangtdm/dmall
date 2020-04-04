package com.dmall.oms.api.dto.listBackground;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.SourceEnum;
import com.dmall.oms.api.enums.CancelTypeEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: PageOrderRequestDTO
 * @author: created by hang.yu on 2020/4/4 16:16
 */
@Data
@ApiModel(value = "PageOrderRequestDTO", description = "后台订单分页请求实体")
public class PageOrderRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "订单来源", position = 2)
    @ValueInEnum(SourceEnum.class)
    private Integer source;

    @ApiModelProperty(value = "会员id", position = 3)
    private Long memberId;

    @ApiModelProperty(value = "订单是否拆分: 1-未拆分;2-无需拆分;3-已拆分;", position = 4)
    @ValueInEnum(SplitEnum.class)
    private Integer isSplit;

    @ApiModelProperty(value = "取消方式", position = 5)
    @ValueInEnum(CancelTypeEnum.class)
    private Integer cancelType;

    @ApiModelProperty(value = "订单状态", position = 6)
    @ValueInEnum(OrderStatusEnum.class)
    private Integer orderStatus;

    @ApiModelProperty(value = "订单创建时间,默认传当天", position = 7)
    private Date createTime = new Date();
}
