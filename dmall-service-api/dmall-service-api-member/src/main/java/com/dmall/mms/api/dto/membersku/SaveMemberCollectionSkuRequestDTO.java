package com.dmall.mms.api.dto.membersku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 会员收藏sku请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@ApiModel(value = "SaveMemberCollectionSkuRequestDTO", description = "会员收藏sku请求实体")
public class SaveMemberCollectionSkuRequestDTO implements Serializable {


    @ApiModelProperty(value = "skuId", position = 1, required = true)
    @NotNull
    private Long skuId;

}
