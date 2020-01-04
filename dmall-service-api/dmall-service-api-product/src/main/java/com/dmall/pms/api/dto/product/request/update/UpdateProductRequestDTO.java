package com.dmall.pms.api.dto.product.request.update;

import com.dmall.pms.api.dto.product.common.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.ProductExtRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改商品请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateProductRequestDTO" , description = "修改商品请求实体" )
public class UpdateProductRequestDTO implements Serializable {

    private static final long serialVersionUID = -3386130384533011334L;

    @ApiModelProperty(value = "商品id" , required = true, position = 1)
    @NotNull(message = "商品id不能为空" )
    private Long id;

    @ApiModelProperty(value = "商品基本信息" , position = 2)
    @Valid
    @NotNull(message = "商品基本信息不能为空" )
    private BasicProductRequestDTO basicProduct;

    @ApiModelProperty(value = "商品属性信息" , position = 3)
    @Valid
    @NotNull(message = "商品属性信息不能为空" )
    private ProductExtRequestDTO ext;
}
