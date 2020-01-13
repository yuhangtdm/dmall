package com.dmall.bms.api.dto.merchants.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.bms.api.dto.merchants.common.CommonMerchantsRequestDTO;

/**
 * @description: 新增商家店铺表 1期只有一家店铺请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveMerchantsRequestDTO", description = "新增商家店铺表 1期只有一家店铺请求实体")
public class SaveMerchantsRequestDTO extends CommonMerchantsRequestDTO {

}
