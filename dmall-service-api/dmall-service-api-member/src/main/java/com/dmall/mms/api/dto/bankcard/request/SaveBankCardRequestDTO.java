package com.dmall.mms.api.dto.bankcard.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.bankcard.common.CommonBankCardRequestDTO;

/**
 * @description: 新增会员银行卡请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SaveBankCardRequestDTO", description="新增会员银行卡请求实体")
public class SaveBankCardRequestDTO extends CommonBankCardRequestDTO {

}
