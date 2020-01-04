package com.dmall.pms.api.dto.product.request.attributevalue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 卖点
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SalePointRequestDTO" , description = "卖点" )
public class SalePointRequestDTO implements Serializable {

    private static final long serialVersionUID = 1441839800494180753L;

    @ApiModelProperty(value = "卖点属性id" , required = true, position = 1)
    @NotNull(message = "卖点属性id不能为空" )
    private Long attributeId;

    @ApiModelProperty(value = "卖点属性值列表" , required = true, position = 2)
    @NotNull(message = "卖点属性值列表不能为空" )
    @Size(min = 1, message = "卖点属性值列表不能为空" )
    private List<String> salePointValues;

}
