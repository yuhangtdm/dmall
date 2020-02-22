package com.dmall.mms.api.dto.advice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.mms.api.dto.advice.common.CommonAdviceRequestDTO;

/**
 * @description: 修改会员意见表 请求实体
 * @author: created by hang.yu on 2020-02-22 23:31:52
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateAdviceRequestDTO", description = "修改会员意见表 请求实体")
public class UpdateAdviceRequestDTO extends CommonAdviceRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
