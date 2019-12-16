package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: sku分页请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageSkuRequestDTO", description =  "sku分页请求实体")
public class PageSkuRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "商家店铺id", position = 3)
    private Long merchantsId;

    @ApiModelProperty(value = "商品分类id", position = 4)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 5)
    private Long brandId;

    @ApiModelProperty(value = "sku编码", position = 6)
    private String skuNo;

    @ApiModelProperty(value = "sku名称", position = 7)
    private String name;

    @ApiModelProperty(value = "sku副名称", position = 8)
    private String subName;

    @ApiModelProperty(value = "sku描述", position = 9)
    private String description;

    @ApiModelProperty(value = "备注", position = 10)
    private String remark;

    @ApiModelProperty(value = "sku主图", position = 11)
    private String pic;

    @ApiModelProperty(value = "价格", position = 12)
    private BigDecimal price;

    @ApiModelProperty(value = "vip价格", position = 13)
    private BigDecimal vipPrice;

    @ApiModelProperty(value = "市场价格", position = 14)
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "品牌名称", position = 15)
    private String brandName;

    @ApiModelProperty(value = "库存", position = 16)
    private Integer stock;

    @ApiModelProperty(value = "预警库存", position = 17)
    private Integer lowStock;

    @ApiModelProperty(value = "锁定库存", position = 18)
    private Integer lockStock;

    @ApiModelProperty(value = "可用库存", position = 19)
    private Integer availableStock;

    @ApiModelProperty(value = "排序", position = 20)
    private Integer sort;

    @ApiModelProperty(value = "sku规格", position = 21)
    private String skuSpecifications;

    @ApiModelProperty(value = "上架状态 Y-上架;N-未上架", position = 22)
    private String publishStatus;

    @ApiModelProperty(value = "上架时间", position = 23)
    private Date onPublishTime;

    @ApiModelProperty(value = "下架时间", position = 24)
    private Date offPublishTime;

    @ApiModelProperty(value = "推荐状态 Y-是;N-否", position = 25)
    private String recommendStatus;

    @ApiModelProperty(value = "新品状态 Y-是;N-否", position = 26)
    private String newStatus;

    @ApiModelProperty(value = "是否是预告sku Y-是;N-否", position = 27)
    private String previewStatus;

    @ApiModelProperty(value = "审核状态 1-未审核;2-审核通过;3-审核不通过", position = 28)
    private Integer auditStatus;

    @ApiModelProperty(value = "优惠方式", position = 29)
    private String preferentialWay;

    @ApiModelProperty(value = "商品分类id集合 如:1/2/3", position = 30)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "skuPC端详情富文本", position = 31)
    private String detailHtml;

    @ApiModelProperty(value = "sku移动端详情富文本", position = 32)
    private String detailMobileHtml;






}
