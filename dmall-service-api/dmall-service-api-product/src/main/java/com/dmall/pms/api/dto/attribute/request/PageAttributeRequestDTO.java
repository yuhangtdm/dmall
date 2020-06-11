package com.dmall.pms.api.dto.attribute.request;

import com.dmall.common.dto.PageRequestDTO;
import com.dmall.common.dto.validate.ValueInEnum;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 属性分页请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageAttributeRequestDTO", description = "商品属性分页请求实体")
public class PageAttributeRequestDTO extends PageRequestDTO {

    @ApiModelProperty(value = "分类id", position = 5)
    private Long categoryId;

    @ApiModelProperty(value = "展示名称", position = 6)
    private String showName;


    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 7)
    @ValueInEnum(InputTypeEnum.class)
    private Integer inputType;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 8)
    @ValueInEnum(HandAddStatusEnum.class)
    private String handAddStatus;

}
