package com.dmall.pms.api.dto.product.request.save;

import com.dmall.pms.api.dto.product.common.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.AddSkuRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.ProductExtRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 新增商品请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveProductRequestDTO" , description = "新增商品请求实体" )
public class SaveProductRequestDTO implements Serializable {

    private static final long serialVersionUID = -1526848004709212956L;

    @ApiModelProperty(value = "商品基本信息" , required = true, position = 1)
    @Valid
    @NotNull(message = "商品基本信息不能为空" )
    private BasicProductRequestDTO basicProduct;

    @ApiModelProperty(value = "商品属性信息" , required = true, position = 2)
    @Valid
    @NotNull(message = "商品属性信息不能为空" )
    private ProductExtRequestDTO ext;

    @ApiModelProperty(value = "sku列表" , position = 3)
    @Valid
    private List<AddSkuRequestDTO> skuList;

}
