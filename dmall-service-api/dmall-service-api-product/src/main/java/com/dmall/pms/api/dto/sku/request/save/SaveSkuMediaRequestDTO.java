package com.dmall.pms.api.dto.sku.request.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 新增sku图片实体
 * @author: created by hang.yu on 2019/12/16 17:41
 */
@Data
@ApiModel(value = "SaveSkuMediaRequestDTO", description = "新增sku图片实体")
public class SaveSkuMediaRequestDTO implements Serializable {

    private static final long serialVersionUID = 7184334204043962937L;

    @ApiModelProperty(value = "属性值id", required = true, position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "sku媒体列表", required = true, position = 2)
    @Valid
    @NotNull(message = "sku媒体列表不能为空")
    @Size(min = 1, message = "sku媒体列表不能为空")
    private List<MediaRequestDTO> mediaList;

}
