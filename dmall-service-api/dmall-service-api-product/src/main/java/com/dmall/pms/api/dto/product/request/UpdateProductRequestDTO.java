package com.dmall.pms.api.dto.product.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.product.common.CommonProductRequestDTO;

/**
 * @description: 修改商品请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UpdateProductRequestDTO", description="修改商品请求实体")
public class UpdateProductRequestDTO extends CommonProductRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
