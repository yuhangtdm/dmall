package com.dmall.pms.api.dto.skuaudit.request;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: sku审核记录分页请求实体
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageSkuAuditRequestDTO", description = "sku审核记录分页请求实体")
public class PageSkuAuditRequestDTO extends PageRequestDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id", position = 5)
    private Long productId;

    @ApiModelProperty(value = "skuId", position = 6)
    private String skuId;

    @ApiModelProperty(value = "审核结果", position = 7)
    @ValueInEnum(SkuAuditStatusEnum.class)
    private Integer status;

}
