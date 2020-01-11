package com.dmall.pms.api.dto.product.request;

import com.dmall.common.dto.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description: 商品分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageProductRequestDTO", description = "商品分页请求实体")
public class PageProductRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "商品分类id", position = 5)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 6)
    private Long brandId;

    @ApiModelProperty(value = "商品编号", position = 7)
    private String productNo;

    @ApiModelProperty(value = "商品名称", position = 8)
    private String name;

    @ApiModelProperty(value = "上市时间起", position = 9)
    private Date onMarketTimeStart;

    @ApiModelProperty(value = "上市时间至", position = 10)
    private Date onMarketTimeEnd;

}
