package com.dmall.pms.api.dto.skuattribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: sku属性值分页请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageSkuAttributeRequestDTO", description =  "sku属性值分页请求实体")
public class PageSkuAttributeRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "skuid", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "product_attribute_id", position = 3)
    private Long productAttributeId;






}
