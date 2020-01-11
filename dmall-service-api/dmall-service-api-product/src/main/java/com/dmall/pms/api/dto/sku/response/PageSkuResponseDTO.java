package com.dmall.pms.api.dto.sku.response;

import com.dmall.common.enums.YNEnum;
import com.dmall.pms.api.dto.sku.enums.SkuAuditStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: PageSkuResponseDTO
 * @author: created by hang.yu on 2019/12/16 20:42
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageSkuResponseDTO", description = "sku分页响应实体")
public class PageSkuResponseDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "品牌id", position = 3)
    private Long brandId;

    @ApiModelProperty(value = "品牌名称", position = 4)
    private String brandName;

    @ApiModelProperty(value = "商品编码", position = 5)
    private String productNo;

    @ApiModelProperty(value = "sku编码", position = 6)
    private String skuNo;

    @ApiModelProperty(value = "sku名称", position = 7)
    private String name;

    @ApiModelProperty(value = "sku主图", position = 6)
    private String pic;

    @ApiModelProperty(value = "价格", position = 9)
    private BigDecimal price;

    @ApiModelProperty(value = "vip价格", position = 10)
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "市场价格", position = 11)
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "库存", position = 12)
    private Integer stock;

    @ApiModelProperty(value = "预警库存", position = 13)
    private Integer lowStock;

    @ApiModelProperty(value = "上架状态", position = 14)
    private YNEnum publishStatus;

    @ApiModelProperty(value = "上架时间", position = 15)
    private Date onPublishTime;

    @ApiModelProperty(value = "下架时间", position = 16)
    private Date offPublishTime;

    @ApiModelProperty(value = "推荐状态", position = 17)
    private YNEnum recommendStatus;

    @ApiModelProperty(value = "新品状态", position = 18)
    private YNEnum newStatus;

    @ApiModelProperty(value = "是否是预告sku", position = 19)
    private YNEnum previewStatus;

    @ApiModelProperty(value = "审核状态", position = 20)
    private SkuAuditStatusEnum auditStatus;

    @ApiModelProperty(value = "排序", position = 21)
    private Integer sort;

    @ApiModelProperty(value = "创建人", position = 22)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 23)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 24)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 25)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 26)
    private String isDeleted;
}
