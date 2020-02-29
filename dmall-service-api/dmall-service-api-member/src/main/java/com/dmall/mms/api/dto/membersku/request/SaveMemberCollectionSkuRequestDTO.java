package com.dmall.mms.api.dto.membersku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 会员收藏sku请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveMemberCollectionSkuRequestDTO", description = "会员收藏sku请求实体")
public class SaveMemberCollectionSkuRequestDTO implements Serializable {


    @ApiModelProperty(value = "skuId", position = 1, required = true)
    @NotNull
    private Long skuId;


}
