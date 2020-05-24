package com.dmall.pms.api.dto.skuaudit.request;

import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.common.enums.YNEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 审核请求实体
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Data
@ApiModel(value = "AuditRequestDTO", description = "审核请求实体")
public class AuditRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审核记录id", required = true, position = 1)
    @NotNull(message = "审核记录id不能为空")
    private Long auditId;

    @ApiModelProperty(value = "审核结果 Y-通过;N-不通过", position = 3)
    @NotBlank(message = "审核结果不能为空")
    @ValueInEnum(YNEnum.class)
    private String result;

    @ApiModelProperty(value = "审核备注", position = 4)
    private String remark;

}
