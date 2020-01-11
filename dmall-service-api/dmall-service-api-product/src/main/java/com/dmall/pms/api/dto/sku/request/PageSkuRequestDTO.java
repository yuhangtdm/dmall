package com.dmall.pms.api.dto.sku.request;

import com.dmall.common.enums.YNEnum;
import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: sku分页请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageSkuRequestDTO", description = "sku分页请求实体")
public class PageSkuRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "商品分类id", position = 5)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 6)
    private Long brandId;

    @ApiModelProperty(value = "商品编码", position = 7)
    private String productNo;

    @ApiModelProperty(value = "sku编码", position = 8)
    private String skuNo;

    @ApiModelProperty(value = "sku名称", position = 9)
    private String name;

    @ApiModelProperty(value = "上架状态 Y-上架;N-未上架", position = 10)
    @ValueInEnum(YNEnum.class)
    private String publishStatus;

    @ApiModelProperty(value = "推荐状态 Y-是;N-否", position = 11)
    @ValueInEnum(YNEnum.class)
    private String recommendStatus;

    @ApiModelProperty(value = "新品状态 Y-是;N-否", position = 12)
    @ValueInEnum(YNEnum.class)
    private String newStatus;

    @ApiModelProperty(value = "是否是预告sku Y-是;N-否", position = 13)
    @ValueInEnum(YNEnum.class)
    private String previewStatus;

    @ApiModelProperty(value = "审核状态 1-未审核;2-审核通过;3-审核不通过", position = 14)
    @ValueInEnum(YNEnum.class)
    private Integer auditStatus;

}
