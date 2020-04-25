package com.dmall.pms.api.dto.skuaudit.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku审核记录响应实体
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Data
@ApiModel(value = "SkuAuditResponseDTO", description = "sku审核记录响应实体")
public class SkuAuditResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "skuid", position = 3)
    private String skuId;

    @ApiModelProperty(value = "审核结果 Y-通过;N-不通过", position = 4)
    private String result;

    @ApiModelProperty(value = "审核备注", position = 5)
    private String remark;

    @ApiModelProperty(value = "创建人", position = 6)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 7)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 8)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 9)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 10)
    private String isDeleted;

}
