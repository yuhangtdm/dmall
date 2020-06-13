package com.dmall.pms.api.dto.product.request;

import com.dmall.common.dto.PageRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 商品分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
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


}
