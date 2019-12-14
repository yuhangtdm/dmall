package com.dmall.pms.api.dto.product.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 商品分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageProductRequestDTO", description="商品分页请求实体")
public class PageProductRequestDTO  extends PageRequestDTO {


    @ApiModelProperty(value = "商品分类id", position = 1)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 2)
    private Long brandId;

    @ApiModelProperty(value = "商品编号", position = 3)
    private String productNo;

    @ApiModelProperty(value = "商品名称", position = 4)
    private String name;

    @ApiModelProperty(value = "上市时间起", position = 5)
    private Date onMarketTimeStart;

    @ApiModelProperty(value = "上市时间至", position = 6)
    private Date onMarketTimeEnd;


}
