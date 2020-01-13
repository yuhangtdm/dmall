package com.dmall.bms.api.dto.merchants.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.merchants.common.CommonMerchantsRequestDTO;

/**
 * @description: 修改商家店铺表 1期只有一家店铺请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateMerchantsRequestDTO", description = "修改商家店铺表 1期只有一家店铺请求实体")
public class UpdateMerchantsRequestDTO extends CommonMerchantsRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
