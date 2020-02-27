package com.dmall.mms.api.dto.memberviewsku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuRequestDTO;

/**
 * @description: 修改会员浏览历史记录请求实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateMemberViewSkuRequestDTO", description = "修改会员浏览历史记录请求实体")
public class UpdateMemberViewSkuRequestDTO extends CommonMemberViewSkuRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
