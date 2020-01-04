package com.dmall.mms.api.dto.bankcard.request;

import com.dmall.mms.api.dto.bankcard.common.CommonBankCardRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 新增会员银行卡请求实体
 * @author: created by hang.yu on 2019-12-02 23:04:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SaveBankCardRequestDTO" , description = "新增会员银行卡请求实体" )
public class SaveBankCardRequestDTO extends CommonBankCardRequestDTO {

}
