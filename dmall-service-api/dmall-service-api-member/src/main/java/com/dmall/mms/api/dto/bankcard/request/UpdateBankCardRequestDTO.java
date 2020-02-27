package com.dmall.mms.api.dto.bankcard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.bankcard.common.CommonBankCardRequestDTO;

/**
 * @description: 修改会员银行卡请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:02
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateBankCardRequestDTO", description = "修改会员银行卡请求实体")
public class UpdateBankCardRequestDTO extends CommonBankCardRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
