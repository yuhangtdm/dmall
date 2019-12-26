package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: SKU规格实体
 * @author: created by hang.yu on 2019/12/26 22:57
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SkuDTO", description = "SKU规格实体")
public class SkuSpecificationsDTO implements Serializable {

    private static final long serialVersionUID = -8325127816306812382L;

    @ApiModelProperty(value = "属性id", position = 1)
    @NotNull(message = "属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "属性值", position = 2)
    @NotNull(message = "属性值不能为空")
    private String attributeValue;

    @ApiModelProperty(value = "属性值配图", position = 3)
    private String pic;
}
