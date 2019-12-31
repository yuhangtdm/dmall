package com.dmall.pms.api.dto.attribute.request;

import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 修改属性请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateAttributeRequestDTO", description = "修改属性请求实体")
public class UpdateAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID = -3857293667290459220L;

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "展示名称", position = 2)
    private String showName;

    @ApiModelProperty(value = "属性类型 1-普通属性;2-公共属性", position = 3)
    @ValueInEnum(TypeEnum.class)
    private Integer type;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 4)
    @ValueInEnum(InputTypeEnum.class)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表", position = 5)
    private List<String> inputList;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 6)
    @ValueInEnum(HandAddStatusEnum.class)
    private String handAddStatus;

    @ApiModelProperty(value = "备注", position = 7)
    private String remark;

}
