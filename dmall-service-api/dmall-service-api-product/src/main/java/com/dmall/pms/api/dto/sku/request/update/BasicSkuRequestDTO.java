package com.dmall.pms.api.dto.sku.request.update;

import com.dmall.common.enums.YNEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description: 修改sku基本信息实体
 * @author: created by hang.yu on 2019/12/16 17:53
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BasicSkuRequestDTO", description = "修改sku基本信息实体")
public class BasicSkuRequestDTO {

    @ApiModelProperty(value = "skuId", required = true, position = 1)
    @NotNull(message = "skuId不能为空")
    private Long id;

    @ApiModelProperty(value = "sku名称", required = true, position = 2)
    @NotBlank(message = "sku名称不能为空")
    private String name;

    @ApiModelProperty(value = "sku副名称", position = 3)
    private String subName;

    @ApiModelProperty(value = "价格", position = 4)
    private BigDecimal price;

    @ApiModelProperty(value = "vip价格", position = 5)
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "市场价格", position = 6)
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "库存", position = 7)
    private Integer stock;

    @ApiModelProperty(value = "预警库存", position = 8)
    private Integer lowStock;

    @ApiModelProperty(value = "推荐状态 Y-是;N-否", position = 9)
    @ValueInEnum(YNEnum.class)
    private String recommendStatus;

    @ApiModelProperty(value = "新品状态 Y-是;N-否", position = 10)
    @ValueInEnum(YNEnum.class)
    private String newStatus;

    @ApiModelProperty(value = "是否是预告sku Y-是;N-否", position = 11)
    @ValueInEnum(YNEnum.class)
    private String previewStatus;

    @ApiModelProperty(value = "sku描述", position = 12)
    private String description;

}
