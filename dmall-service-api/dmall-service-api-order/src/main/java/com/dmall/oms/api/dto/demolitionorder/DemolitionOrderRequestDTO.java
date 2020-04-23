package com.dmall.oms.api.dto.demolitionorder;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.oms.api.enums.SplitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 拆单请求实体
 * @author: created by hang.yu on 2020/4/5 9:49
 */
@Data
@ApiModel(value = "DemolitionOrderRequestDTO", description = "拆单请求实体")
public class DemolitionOrderRequestDTO implements Serializable {

    private static final long serialVersionUID = -9085008153967825057L;

    @ApiModelProperty(value = "订单号", required = true, position = 1)
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @ApiModelProperty(value = "拆单方式", required = true, position = 2)
    @NotNull(message = "拆单方式不能为空")
    @ValueInEnum(SplitEnum.class)
    private Integer split;

    @ApiModelProperty(value = "拆单原因", position = 3)
    private String splitReason;

    @ApiModelProperty(value = "拆单明细", position = 4)
    @Valid
    private List<DemolitionDetailRequestDTO> details;

}
