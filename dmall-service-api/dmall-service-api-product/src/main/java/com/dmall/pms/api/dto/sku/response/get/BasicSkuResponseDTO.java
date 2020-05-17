package com.dmall.pms.api.dto.sku.response.get;

import com.dmall.common.enums.YNEnum;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 修改sku基本信息实体
 * @author: created by hang.yu on 2019/12/16 17:53
 */
@Data
@ApiModel(value = "BasicSkuRequestDTO", description = "查询sku基本信息实体")
public class BasicSkuResponseDTO implements Serializable {

    private static final long serialVersionUID = 6835818836975160962L;

    @ApiModelProperty(value = "skuId", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "sku编码", position = 3)
    private String skuNo;

    @ApiModelProperty(value = "商品编码", position = 4)
    private String productNo;

    @ApiModelProperty(value = "sku名称", position = 5)
    private String name;

    @ApiModelProperty(value = "sku主图", position = 6)
    private String pic;

    @ApiModelProperty(value = "价格", position = 7)
    private BigDecimal price;

    @ApiModelProperty(value = "vip价格", position = 8)
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "市场价格", position = 9)
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "当前可售库存", position = 10)
    private Integer salableStock;

    @ApiModelProperty(value = "锁定库存", position = 10)
    private Integer lockStock;

    @ApiModelProperty(value = "实际库存", position = 10)
    private Integer stock;

    @ApiModelProperty(value = "预警库存", position = 11)
    private Integer lowStock;

    @ApiModelProperty(value = "上架状态", position = 12)
    private YNEnum publishStatus;

    @ApiModelProperty(value = "上架时间", position = 13)
    private Date onPublishTime;

    @ApiModelProperty(value = "下架时间", position = 14)
    private Date offPublishTime;

    @ApiModelProperty(value = "推荐状态", position = 15)
    private YNEnum recommendStatus;

    @ApiModelProperty(value = "新品状态", position = 16)
    private YNEnum newStatus;

    @ApiModelProperty(value = "是否是预告sku", position = 17)
    private YNEnum previewStatus;

    @ApiModelProperty(value = "审核状态", position = 18)
    private SkuAuditStatusEnum auditStatus;

    @ApiModelProperty(value = "sku规格", position = 19)
    private String skuSpecificationsJson;

    @ApiModelProperty(value = "重量", position = 20)
    private String weight;
}
