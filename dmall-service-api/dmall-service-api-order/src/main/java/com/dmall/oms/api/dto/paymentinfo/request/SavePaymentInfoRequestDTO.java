package com.dmall.oms.api.dto.paymentinfo.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.oms.api.dto.paymentinfo.common.CommonPaymentInfoRequestDTO;

/**
 * @description: 新增支付信息请求实体
 * @author: created by hang.yu on 2020-04-02 22:25:15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SavePaymentInfoRequestDTO", description = "新增支付信息请求实体")
public class SavePaymentInfoRequestDTO extends CommonPaymentInfoRequestDTO {

}
