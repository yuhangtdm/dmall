package com.dmall.pms.api.dto.attribute.request;

import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 属性列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListAttributeRequestDTO", description="属性列表请求实体")
public class ListAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "属性分类id", position = 2)
    private Long attributeTypeId;

    @ApiModelProperty(value = "名称", position = 5)
    private String name;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 8)
    @ValueInEnum(InputTypeEnum.class)
    private Integer inputType;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 12)
    @ValueInEnum(HandAddStatusEnum.class)
    private String handAddStatus;

}
