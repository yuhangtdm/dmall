package com.dmall.oms.api.dto.refundrecord.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.oms.api.dto.refundrecord.common.CommonRefundRecordRequestDTO;

/**
 * @description: 修改退款记录请求实体
 * @author: created by hang.yu on 2020-04-13 23:28:05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateRefundRecordRequestDTO", description = "修改退款记录请求实体")
public class UpdateRefundRecordRequestDTO extends CommonRefundRecordRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
