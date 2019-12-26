package com.dmall.pms.api.dto.product.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 卖点
 * @author: created by hang.yu on 2019/12/26 22:29
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SalePointDTO", description = "卖点")
public class SalePointDTO implements Serializable {

    private static final long serialVersionUID = 1441839800494180753L;

    @ApiModelProperty(value = "属性id", position = 1)
    @NotNull(message = "属性id不能为空")
    private Long attributeId;

    @ApiModelProperty(value = "属性值列表", position = 2)
    @NotNull(message = "属性值列表不能为空")
    private List<String> salePointValues;

}
